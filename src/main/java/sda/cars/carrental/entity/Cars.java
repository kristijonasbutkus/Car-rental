package sda.cars.carrental.entity;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Entity
@Table(name = "cars")
public class Cars {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id", nullable = false)
    private Long id;

    @NotBlank(message = "Brand can not be blank ")
    private String brand;

    @NotBlank(message = "Model can not be blank ")
    private String model;

    @Column(name = "body_type")
    private String bodyType;

    @Range(min = 1950, max=2050)
    //@Pattern(regexp = "^[1|2]{1}[0-9]{3}$", message = "Year must be between 1950 - 2050")
    @Column(name = "year")
    private Integer year;

    @Column(name = "color")
    private String color;

    @Column(name = "mileage")
    private Integer mileage;

    @Column(name = "status")
    private String status;

    @Column(name = "seat_count")
    private Integer seatCount;

    @Column(name = "price")
    private BigDecimal price;

    @Column(nullable = true, length = 64)
    private String photo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(Integer seat_count) {
        this.seatCount = seat_count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
