<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                xmlns:java="http://xml.apache.org/xslt/java" exclude-result-prefixes="java"
                xmlns:svg="http://www.w3.org/2000/svg">

    <xsl:output version="1.0" method="xml" encoding="UTF-8" indent="no"/>

    <xsl:decimal-format name="form" decimal-separator="." grouping-separator="&#160;"/>

    <xsl:param name="invoiceNr"/>
    <xsl:param name="hoursWorked"/>
    <xsl:param name="amountWords"/>
    <xsl:param name="amountSum"/>
    <xsl:param name="accountNr"/>
    <xsl:param name="monthInWordsEN"/>
    <xsl:param name="monthInWordsLT"/>
    <xsl:param name="forExpensePeriod"/>

    <xsl:param name="addressLine1"/>
    <xsl:param name="addressLine2"/>
    <xsl:param name="addressLine3"/>

    <xsl:param name="serviceContractNr"/>
    <xsl:param name="serviceDescription"/>

    <xsl:param name="businessName"/>
    <xsl:param name="businessCompanyID"/>
    <xsl:param name="businessManager"/>

    <xsl:template match="/">
        <fo:root font-family="Exo">

            <fo:layout-master-set>
                <fo:simple-page-master master-name="A4-portrait" page-height="297mm" page-width="210mm" margin="20mm">
                    <fo:region-body margin-top="20mm" margin-left="10mm" margin-right="10mm"/>

                    <fo:region-before/>

                    <!-- header - background colour and logo -->
                    <fo:region-after/>

                    <!-- footer  - FF logo and info -->
                    <fo:region-start extent="0mm"/>
                    <fo:region-end extent="0mm"/>

                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="A4-portrait" format="1">
                <fo:static-content flow-name="xsl-region-before">

                    <fo:block-container height="25mm" background-color="#f8f8f0">
                        <fo:block text-align="left" margin-left="4mm" margin-top="1mm" margin-bottom="2mm">
                            <fo:external-graphic src="/images/qr-code.png"
                                 content-height="scale-to-fit" content-width="20mm" />
                        </fo:block>

                        <fo:block border-bottom-width="0.5pt" border-bottom-style="solid" color="#3c3c3c"
                                  margin-top="5mm" margin-bottom="5mm"></fo:block>

                    </fo:block-container>

                </fo:static-content>
                <fo:static-content flow-name="xsl-region-after">
                    <fo:block-container height="25mm">
                        <fo:table>
                            <fo:table-column/>
                            <fo:table-column/>

                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell display-align="after">
                                        <fo:block font-family="Capsuula-Medium" font-size="10pt"
                                                  color="#c8102e">UAB Autoparkas SharedCars &#xa9; 2021
                                        </fo:block>
                                    </fo:table-cell>

                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>

                        <fo:block border-bottom-width="0.5pt" border-bottom-style="solid" color="#3c3c3c"
                                  margin-top="1mm"></fo:block>
                        <fo:table margin-top="3mm">

                            <fo:table-column/>
                            <fo:table-column/>
                            <fo:table-column/>
                            <fo:table-column/>

                            <fo:table-body>
                                <fo:table-row>

                                    <fo:table-cell display-align="after">
                                        <fo:block color="#3c3c3c" font-family="Capsuula-Medium"
                                                  font-weight="bold"
                                                  font-size="8pt">

                                            <fo:inline font-family="Architects-Medium" color="#c8102e">
                                                Company ID
                                            </fo:inline>
                                            304986733
                                        </fo:block>
                                    </fo:table-cell>

                                    <fo:table-cell display-align="after">
                                        <fo:block color="#3c3c3c" font-family="Capsuula-Medium"
                                                  font-weight="bold"
                                                  font-size="8pt">

                                            <fo:inline font-family="Architects-Medium" color="#c8102e">
                                                address
                                            </fo:inline>
                                            K. Būgos g. 12, Vilnius, LT
                                        </fo:block>
                                    </fo:table-cell>

                                    <fo:table-cell display-align="after">
                                        <fo:block color="#3c3c3c" font-family="Capsuula-Medium"
                                                  font-weight="bold"
                                                  font-size="8pt" margin-left="25pt">

                                            <fo:inline font-family="Architects-Medium" color="#c8102e">
                                                phone
                                            </fo:inline>
                                            +370 685 18288
                                        </fo:block>
                                    </fo:table-cell>

                                    <fo:table-cell display-align="after">
                                        <fo:block color="#3c3c3c" font-family="Capsuula-Medium"
                                                  font-weight="bold"
                                                  font-size="8pt" margin-left="15pt">

                                            <fo:inline font-family="Architects-Medium" color="#c8102e">
                                                email
                                            </fo:inline>
                                            sharedpark@gmail.com
                                        </fo:block>
                                    </fo:table-cell>

                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block-container>
                </fo:static-content>

                <fo:flow flow-name="xsl-region-body">
                    <fo:block-container>

                        <fo:block margin-top="-6mm" margin-bottom="2mm"
                                  text-indent="35pt" text-align="left" color="#c8102e"
                                  font-family="Architects-Medium" font-size="25pt">
                            Cars Shared
                        </fo:block>

                        <fo:block text-align="center" margin-top="10pt" margin-bottom="2pt" color="#c8102e"
                                  font-family="Capsuula-Medium" font-size="16pt" >
                            Sąskaita faktūra / Invoice <xsl:value-of select="$invoiceNr"/>
                        </fo:block>

                        <fo:table text-align="left" start-indent="2mm" end-indent="2mm" margin-top="3mm"
                                  background-color="#f8f8f0" border="0.2pt inset #3c3c3c">

                            <fo:table-column column-width="30mm"/>
                            <fo:table-column column-width="50mm"/>

			            <fo:table-column column-width="66mm"/>
                            <fo:table-body>

                                <fo:table-row border-bottom-color="#3c3c3c"
                                              border-bottom-width="1pt">
					      
                                    <fo:table-cell>
                                        <fo:block padding="pt"></fo:block>
                                    </fo:table-cell>

                                </fo:table-row>
				
                                <fo:table-row>
				
                                    <fo:table-cell>
                                        <fo:block color="#3c3c3c" font-family="Exo"
                                                  font-size="10pt">

                                        </fo:block>
                                    </fo:table-cell>
				    
                                    <fo:table-cell>
				    
                                        <fo:block color="#3c3c3c" font-family="Exo"
                                                  font-size="12pt">
                                        </fo:block>
					
					<fo:list-block space-after="1em" space-before="1em">
                                            <fo:list-item>

                                                <fo:list-item-label>
                                                    <fo:block>
                                                        &#x2022;
                                                    </fo:block>
                                                </fo:list-item-label>

                                                <fo:list-item-body start-indent="1em">
                                                    <fo:block color="#3c3c3c" linefeed-treatment="preserve"
                                                              font-family="Exo" font-weight="bold"
                                                              font-size="10pt">Pardavėjas / Provider:
                                                    </fo:block>

                                                    <fo:block color="#3c3c3c" linefeed-treatment="preserve"
                                                              font-family="Exo"
                                                              font-size="10pt">UAB Autoparkas SharedCars

                                                    </fo:block>

                                                </fo:list-item-body>
                                            </fo:list-item>
					</fo:list-block>

                                    </fo:table-cell>
				    
                                    <fo:table-cell>
                                        <fo:block color="#3c3c3c" font-family="Exo"
                                                  font-size="12pt">
                                        </fo:block>

                                        <fo:list-block space-after="1em" space-before="1em">

                                            <fo:list-item>
                                                <fo:list-item-label>
                                                    <fo:block>
                                                        &#x2022;
                                                    </fo:block>
                                                </fo:list-item-label>
                                                <fo:list-item-body start-indent="1em">

                                                    <fo:block color="#3c3c3c" linefeed-treatment="preserve"
                                                              font-family="Exo" font-weight="bold"
                                                              font-size="10pt">Pirkėjas / Client:
                                                    </fo:block>

                                                    <fo:block color="#3c3c3c" linefeed-treatment="preserve"
                                                              font-family="Exo"
                                                              font-size="10pt">
                                                        <xsl:value-of select="$businessName"/>&#xA0;
                                                        <xsl:value-of select="$addressLine1"/>
                                                        <xsl:value-of select="$addressLine2"/> <xsl:value-of select="$addressLine3"/>

                                                        <xsl:if test="$businessCompanyID != ''">
                                                            Įm. kodas <xsl:value-of select="$businessCompanyID"/>
                                                        </xsl:if>

                                                    </fo:block>
                                                </fo:list-item-body>
                                            </fo:list-item>
                                        </fo:list-block>
					
                                    </fo:table-cell>

                                </fo:table-row>
				</fo:table-body>
				
				</fo:table>
				
				
			<!-- EUR amount -->
                        <fo:table text-align="left" start-indent="2mm" end-indent="2mm" 
			margin-top="2mm" margin-bottom="2mm"
                                  background-color="#f8f8f0" border="0.2pt inset #3c3c3c">

                            <fo:table-column column-width="146mm"/>
                            <fo:table-body>
				
                                <fo:table-row>
				
                                    <fo:table-cell>
				    
                                        <fo:block padding="5pt"></fo:block>
				    
                                        <fo:block space-before="5pt" color="#3c3c3c"
                                                  font-family="Exo"
                                                  font-size="10pt">Sąskaitos išrašymo data / Invoice issued on:
                                            <xsl:value-of select="java:format(
                                            java:java.text.SimpleDateFormat.new(' yyyy.MM.dd'),
                                            java:java.util.Date.new())"/>

                                        </fo:block>

                                        <fo:block color="#3c3c3c" font-family="Exo"
                                                  font-size="10pt">
							      
                                        <fo:block margin-top="4pt" margin-left="4pt" margin-bottom="0pt" color="#3c3c3c" linefeed-treatment="preserve"
                                                  font-family="Exo"
                                                  font-size="10pt">Mokėti į / Payment to: „Perversk“ UAB
                                            &#160;
                                            &#160; Rinktinės g. 5,
                                            &#160; Vilnius,
                                            &#160; LT-09234
                                            &#160;
                                            &#160; Gavėjas / Beneficiary: UAB Autoparkas SharedCars
                                            &#160; BIC UAPELT22XXX
                                            &#160; LT39 7300 0101 3460 5284
                                        </fo:block>

                                        <fo:block space-before="5pt" color="#3c3c3c"
                                                  font-family="Exo" font-weight="bold"
                                                  font-size="10pt">
                                            <xsl:text>&#x2022; Pavedimo paskirtis / Reference: </xsl:text>
                                            <xsl:value-of select="$serviceContractNr"/>

                                        </fo:block>

                                        <fo:list-block space-after="1em" space-before="1em">
<!--                                            <fo:list-item>
                                                <fo:list-item-label>
                                                    <fo:block>
                                                        &#x2022;
                                                    </fo:block>
                                                </fo:list-item-label>
                                                <fo:list-item-body start-indent="1em">
                                                    <fo:block color="#3c3c3c"
                                                              font-family="Exo"
                                                              font-size="10pt">

                                                    <xsl:text>&#xA0;Sąskaita už / For service:</xsl:text>&#xA0;
                                                        <xsl:value-of select="$serviceDescription"/>.&#xA0;

                                                    </fo:block>
                                                </fo:list-item-body>
                                            </fo:list-item>
-->

                                            <fo:list-item>
                                                <fo:list-item-label>
                                                    <fo:block>
                                                        &#x2022;
                                                    </fo:block>
                                                </fo:list-item-label>

                                                <fo:list-item-body start-indent="1em">
                                                    <fo:block color="#3c3c3c"
                                                              font-family="Exo"
                                                              font-size="10pt">

                                                        <xsl:text>&#xA0;Apmokėti iki: </xsl:text>
                                                        <xsl:value-of select="$monthInWordsLT"/>&#xA0;d. &#xA0;/

                                                        <xsl:text>Payment due by: </xsl:text>
                                                        <xsl:value-of select="$monthInWordsEN"/>.&#xA0;

                                                    </fo:block>
                                                </fo:list-item-body>
                                            </fo:list-item>

                                            <fo:list-item>
                                                <fo:list-item-label>
                                                    <fo:block>
                                                        &#x2028;
                                                    </fo:block>
                                                </fo:list-item-label>

                                                <fo:list-item-body start-indent="1em">
                                                    <fo:block color="#3c3c3c"
                                                              font-family="Exo"
                                                              font-size="10pt">

                                                    </fo:block>
                                                </fo:list-item-body>
                                            </fo:list-item>

                                        </fo:list-block>

					</fo:block>
										
					</fo:table-cell>
					
					</fo:table-row>
					
					</fo:table-body>
					
					</fo:table>
				
                        <fo:table text-align="left" start-indent="2mm" end-indent="2mm" 
				margin-top="2mm" margin-bottom="2mm"
                                  background-color="#f8f8f0" border="0.2pt inset #3c3c3c">

                            <fo:table-column column-width="30mm"/>
                            <fo:table-column column-width="50mm"/>
			    <fo:table-column column-width="66mm"/>
                            				
			    <fo:table-body> 
			    
				<fo:table-row>
				
                                    <fo:table-cell>
                                        <fo:block padding="5pt"></fo:block>
                                    </fo:table-cell>			

				</fo:table-row>
				
                                <fo:table-row>
                                    <fo:table-cell>
                                        <fo:block color="#3c3c3c" margin-top="6pt" font-family="Exo"
                                                  font-size="10pt">Suma / Amount:
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block color="#3c3c3c" margin-top="4pt" font-family="Exo"
                                                  font-size="10pt">
                                            <fo:instream-foreign-object>
                                                <svg:svg width="100mm" height="5mm">
                                                    <svg:rect rx="5pt" ry="5pt" width="100mm" height="5mm"
                                                              style="fill:white;stroke-width:1pt;stroke:#3c3c3c"/>
                                                    <svg:text x="5mm" y="4mm"
                                                              style="
                                                              font-family   :   Exo;
                                                              font-size     :   10pt;
                                                              stroke        :   #3c3c3c;
                                                              fill          :   #3c3c3c;
                                                              stroke-width  :   0.5pt">
                                                        <xsl:value-of select="$amountSum"/>
                                                    </svg:text>
                                                </svg:svg>
                                            </fo:instream-foreign-object>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>

                                <fo:table-row>
                                    <fo:table-cell>
                                        <fo:block padding="5pt"></fo:block>
                                    </fo:table-cell>

                                    <fo:table-cell>
                                        <fo:block padding="5pt"></fo:block>
                                    </fo:table-cell>
                                </fo:table-row>

                                <!-- AMOUNT -->
                                <fo:table-row>
                                    <fo:table-cell>
                                        <fo:block color="#3c3c3c" font-family="Exo"
                                                  font-size="10pt">Suma žodžiais / Amount in words:
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block color="#3c3c3c" font-family="Exo"
                                                  font-size="10pt">
                                            <fo:instream-foreign-object>
                                                <svg:svg width="100mm" height="5mm">
                                                    <svg:rect rx="5pt" ry="5pt" width="100mm" height="5mm"
                                                              style="fill:white;stroke-width:1pt;stroke:#3c3c3c"/>
                                                    <svg:text x="5mm" y="4mm"
                                                              style="
                                                              font-family   :   Exo;
                                                              font-size     :   8pt;
                                                              stroke        :   #3c3c3c;
                                                              fill          :   #3c3c3c;
                                                              stroke-width  :   0.5pt">
                                                        <xsl:value-of select="$amountWords"/>
                                                    </svg:text>
                                                </svg:svg>
                                            </fo:instream-foreign-object>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>

                                <fo:table-row>
                                    <fo:table-cell>
                                        <fo:block padding="5pt"></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block padding="5pt"></fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell>
                                        <fo:block color="#3c3c3c" margin-top="2pt" linefeed-treatment="preserve" font-family="Exo"
                                                  font-size="10pt">Gavėjo sąskaita / 
						  Beneficiary account:
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block color="#3c3c3c" font-family="Exo"
                                                  font-size="10pt">
                                            <fo:instream-foreign-object>
                                                <svg:svg width="100mm" height="5mm">

                                                    <svg:rect rx="5pt" ry="5pt" width="100mm" height="5mm"
                                                              style="fill:white;stroke-width:1pt;stroke:#3c3c3c"/>
                                                    <svg:text x="5mm" y="4mm"
                                                              style="
                                                              font-family   :   Exo;
                                                              font-size     :   10pt;
                                                              stroke        :   #3c3c3c;
                                                              fill          :   #3c3c3c;
                                                              stroke-width  :   0.5pt">
                                                        <xsl:value-of select="$accountNr"/>
                                                    </svg:text>

                                                </svg:svg>
                                            </fo:instream-foreign-object>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>

                                <fo:table-row>

                                    <fo:table-cell>

                                        <fo:block color="#3c3c3c" margin-top="14pt" margin-bottom="1pt"
                                                  font-family="Exo"
                                                  font-size="10pt">Sąskaitą išrašė / Invoice issued by:
                                        </fo:block>

                                    </fo:table-cell>

                                    <fo:table-cell font-family="Exo" >

                                        <fo:block color="#3c3c3c" margin-top="2pt"
                                                  linefeed-treatment="preserve"
                                                  font-family="Exo" font-weight="bold"
                                                  font-size="10pt">&#160; N. Arbačiauskas
                                            &#160;
                                            &#160; Financial Manager /
                                            &#160; Buhalteris
                                            <!--xsl:value-of select="$businessManager"/-->

                                            <fo:external-graphic src="/images/signature.png"
                                                                 content-height="scale-to-fit" content-width="25mm"
                                                                 margin-left="200mm"
                                                                 margin-right="10pt"
                                                                 margin-top="0pt"
                                                                 scaling="uniform"/>

                                        </fo:block>

                                    </fo:table-cell>
                                </fo:table-row>

                                <fo:table-row>
                                    <fo:table-cell>
                                        <fo:block color="#3c3c3c" margin-top="0pt" margin-bottom="4pt"
					font-family="Exo" font-size="10pt">Data / Signed on:
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block color="#3c3c3c" font-family="Exo"
                                                  font-size="10pt">
                                            <fo:instream-foreign-object>
                                                <svg:svg width="50mm" height="5mm">
                                                    <svg:rect rx="5pt" ry="5pt" width="50mm" height="5mm"
                                                              style="fill:white;stroke-width:1pt;stroke:#3c3c3c"/>

                                                    <svg:text x="5mm" y="4mm" font-family="Exo"
                                                              style="
                                                              font-size     :   10pt;
                                                              stroke        :   #3c3c3c;
                                                              fill          :   #3c3c3c;
                                                              stroke-width  :   0.5pt">

                                                        <xsl:value-of select="java:format(
                                            java:java.text.SimpleDateFormat.new(' yyyy.MM.dd'),
                                            java:java.util.Date.new())"/>

                                                    </svg:text>
                                                </svg:svg>
                                            </fo:instream-foreign-object>

                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row> 

                            </fo:table-body>
                        </fo:table>
			
                        <fo:table text-align="left" start-indent="2mm" end-indent="2mm" margin-top="0mm"
                                  background-color="#f8f8f0" border="0.2pt inset #3c3c3c">

                            <fo:table-column column-width="146mm"/>
			    
                            <fo:table-body>
			    
                                <fo:table-row>
				
				   <fo:table-cell>

                                        <fo:block margin-top="4pt" start-indent="5pt" end-indent="5pt" text-align="left" 
					color="#3c3c3c" font-family="Exo"
                                                  font-size="9pt">&#x2022; Ši sąskaita šalių laikoma paslaugos priėmimo-perdavimo akto galią turinčiu dokumentu.&#x2028;
                                            &#x2022; Pirkėjas priima paslaugą ir šalys neturi pretenzijų viena kitai.
                                        </fo:block>

                                        <fo:block margin-top="4pt" start-indent="5pt" end-indent="5pt" text-align="left" 
					color="#3c3c3c" font-family="Exo"
                                                  font-size="9pt">&#x2022; This invoice counts as an act of acceptance of works.
                                        </fo:block>

                                    </fo:table-cell>
				    

				</fo:table-row>
				
				<fo:table-row>
				
                                    <fo:table-cell>
                                        <fo:block padding="5pt"></fo:block>
                                    </fo:table-cell>			

				</fo:table-row>
		
				</fo:table-body>
					
			</fo:table>
							

                    </fo:block-container>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
