package sda.cars.carrental.printout.services;

import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.DefaultConfigurationBuilder;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.events.LoggingEventListener;
import org.apache.xmlgraphics.util.MimeConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.URIResolver;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


public class PDFGeneratorServiceImpl implements PDFGeneratorService {
    private static final String PDF_CLASS_PATH = "/pdf";

    private static final Logger LOG = LoggerFactory.getLogger(PDFGeneratorServiceImpl.class);

    private Map<String, Template> CACHE = new HashMap<String, Template>(10);

    public byte[] generatePDF(String templateName, Map<String, Object> params) throws PDFGeneratorServiceException {
        final String templateFolder = PDF_CLASS_PATH + "/" + templateName;

        try {
            Template templ = getTemplate(templateFolder, templateName);

            FopFactory fopFactory = templ.getFopFactory();
            Transformer transformer = templ.getTransformer();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            //Setup FOP
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);

            fop.getUserAgent().setURIResolver(templ.getDocumentUriResolver());
            fop.getUserAgent().getEventBroadcaster().addEventListener(new LoggingEventListener());

            //Make sure the XSL transformation's result is piped through to FOP
            Result res = new SAXResult(fop.getDefaultHandler());

            //Start the transformation and rendering process
            String xml = (String) params.get(XML_DATA_ATTR);
            if (xml == null) {
                xml = "<empty/>";
            }
            ByteArrayInputStream bais = new ByteArrayInputStream(xml.getBytes());
            Source src = new StreamSource(bais);

            for (Map.Entry<String, Object> e : params.entrySet()) {
                if (e.getValue() != null) {
                    transformer.setParameter(
                            e.getKey(),
                            e.getValue()
                    );
                }
            }
            transformer.transform(src, res);
            return out.toByteArray();
        } catch (Exception e) {
            LOG.error("Can't build PDF.", e);
            throw new PDFGeneratorServiceException(e.getMessage(), e);
        }
    }

    private Template getTemplate(String templateFolder, String templateName) throws Exception {
        final String templatePath = templateFolder + "/template.xsl";
        Template templ = CACHE.get(templateName);
        if (templ == null) {
            TransformerFactory tFactory = TransformerFactory.newInstance();

            InputStream xsltStream = PDFGeneratorServiceImpl.class.getResourceAsStream(templatePath);
            Transformer transformer = tFactory.newTransformer(new StreamSource(xsltStream));
            transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");

            transformer.setErrorListener(new ErrorListenerImpl());
            URIResolver documentUriResolver = new URIResolverImpl(templateFolder);

            transformer.setURIResolver(documentUriResolver);
            transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");

            FopFactory fopFactory = FopFactory.newInstance();
            fopFactory.setURIResolver(documentUriResolver);
            DefaultConfigurationBuilder cfgBuilder = new DefaultConfigurationBuilder();
            String fopConfigPath = templateFolder + "/fop-config.xml";
            InputStream fopConfig = PDFGeneratorServiceImpl.class.getResourceAsStream(fopConfigPath);
            if (fopConfig == null) {
                throw new FileNotFoundException(fopConfigPath + " not found.");
            }
            Configuration cfg = cfgBuilder.build(fopConfig);
            fopFactory.setUserConfig(cfg);

            templ = new Template(fopFactory, transformer, documentUriResolver);
            CACHE.put(templateName, templ);
        }
        return templ;
    }

    private class URIResolverImpl implements URIResolver {
        private String templateFolder;

        private URIResolverImpl(String templateFolder) {
            this.templateFolder = templateFolder;
        }

        public Source resolve(String href, String base) throws TransformerException {
            String refPath = templateFolder + "/" + href;
            if (base == null) {
                refPath = href;
            }
            InputStream stream;
            if (refPath.startsWith("file:")) {
                try {
                    stream = new FileInputStream(refPath.substring(5));
                } catch (FileNotFoundException e) {
                    throw new TransformerException(e);
                }
            } else {
                stream = PDFGeneratorServiceImpl.class.getResourceAsStream(refPath);
            }
            if (stream == null) {
                LOG.error("Resource not found. href=" + href + "; base=" + base);
                throw new TransformerException("Resource not found. href=" + href + "; base=" + base);
            }
            return new StreamSource(stream);
        }
    }

    private class ErrorListenerImpl implements ErrorListener {
        public void warning(TransformerException exception) throws TransformerException {
            LOG.warn(exception.getMessage());
        }

        public void error(TransformerException exception) throws TransformerException {
            LOG.error(exception.getMessage(), exception);
        }

        public void fatalError(TransformerException exception) throws TransformerException {
            LOG.error(exception.getMessage(), exception);
        }
    }

    private class Template {
        private final FopFactory fopFactory;
        private final Transformer transformer;
        private final URIResolver documentUriResolver;

        private Template(FopFactory fopFactory, Transformer transformer, URIResolver documentUriResolver) {
            this.fopFactory = fopFactory;
            this.transformer = transformer;
            this.documentUriResolver = documentUriResolver;
        }

        public FopFactory getFopFactory() {
            return fopFactory;
        }

        public Transformer getTransformer() {
            return transformer;
        }

        public URIResolver getDocumentUriResolver() {
            return documentUriResolver;
        }
    }
}
