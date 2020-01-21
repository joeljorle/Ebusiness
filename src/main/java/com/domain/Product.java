package com.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.domain.enumeration.Type;

/**
 * A Product.
 */
@Entity
@Table(name = "product")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "categoryid", nullable = false)
    private Integer categoryid;

    @NotNull
    @Column(name = "slug", nullable = false)
    private String slug;

    @Column(name = "name")
    private String name;

    @Column(name = "islock")
    private Boolean islock;

    @Column(name = "lockdelay")
    private Instant lockdelay;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Type type;

    @Column(name = "createdat")
    private Instant createdat;

    @Column(name = "updatedat")
    private Instant updatedat;

    @OneToOne
    @JoinColumn(unique = true)
    private Files files;

    @OneToOne
    @JoinColumn(unique = true)
    private Productdetails productdetails;

    @OneToOne
    @JoinColumn(unique = true)
    private Currency currency;

    @OneToOne
    @JoinColumn(unique = true)
    private Location location;

    /**
     * product to many relationship.
     */
    @OneToMany(mappedBy = "product")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Tour> tours = new HashSet<>();

    @OneToMany(mappedBy = "product")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Evenement> evenements = new HashSet<>();

    @OneToMany(mappedBy = "product")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Tourgroup> tourgroups = new HashSet<>();

    @OneToMany(mappedBy = "product")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Tourcategory> tourcategories = new HashSet<>();

    @OneToMany(mappedBy = "product")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Likes> likes = new HashSet<>();

    @OneToMany(mappedBy = "product")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Follows> follows = new HashSet<>();

    @OneToMany(mappedBy = "product")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Ratings> ratings = new HashSet<>();

    @OneToMany(mappedBy = "product")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Reviews> reviews = new HashSet<>();

    @OneToMany(mappedBy = "product")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Paymentaction> paymentactions = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("products")
    private Category category;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public Product categoryid(Integer categoryid) {
        this.categoryid = categoryid;
        return this;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public String getSlug() {
        return slug;
    }

    public Product slug(String slug) {
        this.slug = slug;
        return this;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public Product name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isIslock() {
        return islock;
    }

    public Product islock(Boolean islock) {
        this.islock = islock;
        return this;
    }

    public void setIslock(Boolean islock) {
        this.islock = islock;
    }

    public Instant getLockdelay() {
        return lockdelay;
    }

    public Product lockdelay(Instant lockdelay) {
        this.lockdelay = lockdelay;
        return this;
    }

    public void setLockdelay(Instant lockdelay) {
        this.lockdelay = lockdelay;
    }

    public Type getType() {
        return type;
    }

    public Product type(Type type) {
        this.type = type;
        return this;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Instant getCreatedat() {
        return createdat;
    }

    public Product createdat(Instant createdat) {
        this.createdat = createdat;
        return this;
    }

    public void setCreatedat(Instant createdat) {
        this.createdat = createdat;
    }

    public Instant getUpdatedat() {
        return updatedat;
    }

    public Product updatedat(Instant updatedat) {
        this.updatedat = updatedat;
        return this;
    }

    public void setUpdatedat(Instant updatedat) {
        this.updatedat = updatedat;
    }

    public Files getFiles() {
        return files;
    }

    public Product files(Files files) {
        this.files = files;
        return this;
    }

    public void setFiles(Files files) {
        this.files = files;
    }

    public Productdetails getProductdetails() {
        return productdetails;
    }

    public Product productdetails(Productdetails productdetails) {
        this.productdetails = productdetails;
        return this;
    }

    public void setProductdetails(Productdetails productdetails) {
        this.productdetails = productdetails;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Product currency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Location getLocation() {
        return location;
    }

    public Product location(Location location) {
        this.location = location;
        return this;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<Tour> getTours() {
        return tours;
    }

    public Product tours(Set<Tour> tours) {
        this.tours = tours;
        return this;
    }

    public Product addTour(Tour tour) {
        this.tours.add(tour);
        tour.setProduct(this);
        return this;
    }

    public Product removeTour(Tour tour) {
        this.tours.remove(tour);
        tour.setProduct(null);
        return this;
    }

    public void setTours(Set<Tour> tours) {
        this.tours = tours;
    }

    public Set<Evenement> getEvenements() {
        return evenements;
    }

    public Product evenements(Set<Evenement> evenements) {
        this.evenements = evenements;
        return this;
    }

    public Product addEvenement(Evenement evenement) {
        this.evenements.add(evenement);
        evenement.setProduct(this);
        return this;
    }

    public Product removeEvenement(Evenement evenement) {
        this.evenements.remove(evenement);
        evenement.setProduct(null);
        return this;
    }

    public void setEvenements(Set<Evenement> evenements) {
        this.evenements = evenements;
    }

    public Set<Tourgroup> getTourgroups() {
        return tourgroups;
    }

    public Product tourgroups(Set<Tourgroup> tourgroups) {
        this.tourgroups = tourgroups;
        return this;
    }

    public Product addTourgroup(Tourgroup tourgroup) {
        this.tourgroups.add(tourgroup);
        tourgroup.setProduct(this);
        return this;
    }

    public Product removeTourgroup(Tourgroup tourgroup) {
        this.tourgroups.remove(tourgroup);
        tourgroup.setProduct(null);
        return this;
    }

    public void setTourgroups(Set<Tourgroup> tourgroups) {
        this.tourgroups = tourgroups;
    }

    public Set<Tourcategory> getTourcategories() {
        return tourcategories;
    }

    public Product tourcategories(Set<Tourcategory> tourcategories) {
        this.tourcategories = tourcategories;
        return this;
    }

    public Product addTourcategory(Tourcategory tourcategory) {
        this.tourcategories.add(tourcategory);
        tourcategory.setProduct(this);
        return this;
    }

    public Product removeTourcategory(Tourcategory tourcategory) {
        this.tourcategories.remove(tourcategory);
        tourcategory.setProduct(null);
        return this;
    }

    public void setTourcategories(Set<Tourcategory> tourcategories) {
        this.tourcategories = tourcategories;
    }

    public Set<Likes> getLikes() {
        return likes;
    }

    public Product likes(Set<Likes> likes) {
        this.likes = likes;
        return this;
    }

    public Product addLikes(Likes likes) {
        this.likes.add(likes);
        likes.setProduct(this);
        return this;
    }

    public Product removeLikes(Likes likes) {
        this.likes.remove(likes);
        likes.setProduct(null);
        return this;
    }

    public void setLikes(Set<Likes> likes) {
        this.likes = likes;
    }

    public Set<Follows> getFollows() {
        return follows;
    }

    public Product follows(Set<Follows> follows) {
        this.follows = follows;
        return this;
    }

    public Product addFollows(Follows follows) {
        this.follows.add(follows);
        follows.setProduct(this);
        return this;
    }

    public Product removeFollows(Follows follows) {
        this.follows.remove(follows);
        follows.setProduct(null);
        return this;
    }

    public void setFollows(Set<Follows> follows) {
        this.follows = follows;
    }

    public Set<Ratings> getRatings() {
        return ratings;
    }

    public Product ratings(Set<Ratings> ratings) {
        this.ratings = ratings;
        return this;
    }

    public Product addRatings(Ratings ratings) {
        this.ratings.add(ratings);
        ratings.setProduct(this);
        return this;
    }

    public Product removeRatings(Ratings ratings) {
        this.ratings.remove(ratings);
        ratings.setProduct(null);
        return this;
    }

    public void setRatings(Set<Ratings> ratings) {
        this.ratings = ratings;
    }

    public Set<Reviews> getReviews() {
        return reviews;
    }

    public Product reviews(Set<Reviews> reviews) {
        this.reviews = reviews;
        return this;
    }

    public Product addReviews(Reviews reviews) {
        this.reviews.add(reviews);
        reviews.setProduct(this);
        return this;
    }

    public Product removeReviews(Reviews reviews) {
        this.reviews.remove(reviews);
        reviews.setProduct(null);
        return this;
    }

    public void setReviews(Set<Reviews> reviews) {
        this.reviews = reviews;
    }

    public Set<Paymentaction> getPaymentactions() {
        return paymentactions;
    }

    public Product paymentactions(Set<Paymentaction> paymentactions) {
        this.paymentactions = paymentactions;
        return this;
    }

    public Product addPaymentaction(Paymentaction paymentaction) {
        this.paymentactions.add(paymentaction);
        paymentaction.setProduct(this);
        return this;
    }

    public Product removePaymentaction(Paymentaction paymentaction) {
        this.paymentactions.remove(paymentaction);
        paymentaction.setProduct(null);
        return this;
    }

    public void setPaymentactions(Set<Paymentaction> paymentactions) {
        this.paymentactions = paymentactions;
    }

    public Category getCategory() {
        return category;
    }

    public Product category(Category category) {
        this.category = category;
        return this;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        return id != null && id.equals(((Product) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Product{" +
            "id=" + getId() +
            ", categoryid=" + getCategoryid() +
            ", slug='" + getSlug() + "'" +
            ", name='" + getName() + "'" +
            ", islock='" + isIslock() + "'" +
            ", lockdelay='" + getLockdelay() + "'" +
            ", type='" + getType() + "'" +
            ", createdat='" + getCreatedat() + "'" +
            ", updatedat='" + getUpdatedat() + "'" +
            "}";
    }
}
