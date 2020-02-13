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

import com.domain.enumeration.Tagcolor;

/**
 * A Evenement.
 */
@Entity
@Table(name = "evenement")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Evenement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "categoryid")
    private Integer categoryid;

    @Column(name = "userid")
    private Integer userid;

    @Column(name = "productid")
    private Integer productid;

    @NotNull
    @Column(name = "slug", nullable = false)
    private String slug;

    @Column(name = "name")
    private String name;

    @Column(name = "islock")
    private Boolean islock;

    @Column(name = "lockdelay")
    private Instant lockdelay;

    @Column(name = "islimited")
    private Boolean islimited;

    @Column(name = "limiteddelay")
    private Instant limiteddelay;

    @Column(name = "limitedbooking")
    private Integer limitedbooking;

    @Column(name = "startdate")
    private Instant startdate;

    @Column(name = "enddate")
    private Instant enddate;

    @Lob
    @Column(name = "about")
    private String about;

    @Column(name = "title")
    private String title;

    @Column(name = "tag")
    private String tag;

    @Enumerated(EnumType.STRING)
    @Column(name = "tagcolor")
    private Tagcolor tagcolor;

    @Column(name = "postalcode")
    private String postalcode;

    @Column(name = "phones")
    private String phones;

    @Column(name = "website")
    private String website;

    @Column(name = "facebook")
    private String facebook;

    @Column(name = "twitter")
    private String twitter;

    @Column(name = "gplus")
    private String gplus;

    @Column(name = "linkedin")
    private String linkedin;

    @Column(name = "instagram")
    private String instagram;

    @Column(name = "email")
    private String email;

    @Column(name = "url")
    private String url;

    @Lob
    @Column(name = "othercontacts")
    private String othercontacts;

    @Lob
    @Column(name = "otherfields")
    private String otherfields;

    @Column(name = "createdat")
    private Instant createdat;

    @Column(name = "updatedat")
    private Instant updatedat;

    @OneToOne
    @JoinColumn(unique = true)
    private Files files;

    @OneToOne
    @JoinColumn(unique = true)
    private Currency currency;

    @OneToOne
    @JoinColumn(unique = true)
    private Location location;

    /**
     * Evenement to many relationship.
     */
    @OneToMany(mappedBy = "evenement")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Tour> tours = new HashSet<>();

    @OneToMany(mappedBy = "evenement")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Tourgroup> tourgroups = new HashSet<>();

    @OneToMany(mappedBy = "evenement")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Tourcategory> tourcategories = new HashSet<>();

    @OneToMany(mappedBy = "evenement")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Likes> likes = new HashSet<>();

    @OneToMany(mappedBy = "evenement")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Ratings> ratings = new HashSet<>();

    @OneToMany(mappedBy = "evenement")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Reviews> reviews = new HashSet<>();

    @OneToMany(mappedBy = "evenement")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Follows> follows = new HashSet<>();

    @OneToMany(mappedBy = "evenement")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Booking> bookings = new HashSet<>();

    @OneToMany(mappedBy = "evenement")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Paymentaction> paymentactions = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("evenements")
    private Category category;

    @ManyToOne
    @JsonIgnoreProperties("evenements")
    private Product product;

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

    public Evenement categoryid(Integer categoryid) {
        this.categoryid = categoryid;
        return this;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public Integer getUserid() {
        return userid;
    }

    public Evenement userid(Integer userid) {
        this.userid = userid;
        return this;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getProductid() {
        return productid;
    }

    public Evenement productid(Integer productid) {
        this.productid = productid;
        return this;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public String getSlug() {
        return slug;
    }

    public Evenement slug(String slug) {
        this.slug = slug;
        return this;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public Evenement name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isIslock() {
        return islock;
    }

    public Evenement islock(Boolean islock) {
        this.islock = islock;
        return this;
    }

    public void setIslock(Boolean islock) {
        this.islock = islock;
    }

    public Instant getLockdelay() {
        return lockdelay;
    }

    public Evenement lockdelay(Instant lockdelay) {
        this.lockdelay = lockdelay;
        return this;
    }

    public void setLockdelay(Instant lockdelay) {
        this.lockdelay = lockdelay;
    }

    public Boolean isIslimited() {
        return islimited;
    }

    public Evenement islimited(Boolean islimited) {
        this.islimited = islimited;
        return this;
    }

    public void setIslimited(Boolean islimited) {
        this.islimited = islimited;
    }

    public Instant getLimiteddelay() {
        return limiteddelay;
    }

    public Evenement limiteddelay(Instant limiteddelay) {
        this.limiteddelay = limiteddelay;
        return this;
    }

    public void setLimiteddelay(Instant limiteddelay) {
        this.limiteddelay = limiteddelay;
    }

    public Integer getLimitedbooking() {
        return limitedbooking;
    }

    public Evenement limitedbooking(Integer limitedbooking) {
        this.limitedbooking = limitedbooking;
        return this;
    }

    public void setLimitedbooking(Integer limitedbooking) {
        this.limitedbooking = limitedbooking;
    }

    public Instant getStartdate() {
        return startdate;
    }

    public Evenement startdate(Instant startdate) {
        this.startdate = startdate;
        return this;
    }

    public void setStartdate(Instant startdate) {
        this.startdate = startdate;
    }

    public Instant getEnddate() {
        return enddate;
    }

    public Evenement enddate(Instant enddate) {
        this.enddate = enddate;
        return this;
    }

    public void setEnddate(Instant enddate) {
        this.enddate = enddate;
    }

    public String getAbout() {
        return about;
    }

    public Evenement about(String about) {
        this.about = about;
        return this;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getTitle() {
        return title;
    }

    public Evenement title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public Evenement tag(String tag) {
        this.tag = tag;
        return this;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Tagcolor getTagcolor() {
        return tagcolor;
    }

    public Evenement tagcolor(Tagcolor tagcolor) {
        this.tagcolor = tagcolor;
        return this;
    }

    public void setTagcolor(Tagcolor tagcolor) {
        this.tagcolor = tagcolor;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public Evenement postalcode(String postalcode) {
        this.postalcode = postalcode;
        return this;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getPhones() {
        return phones;
    }

    public Evenement phones(String phones) {
        this.phones = phones;
        return this;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }

    public String getWebsite() {
        return website;
    }

    public Evenement website(String website) {
        this.website = website;
        return this;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFacebook() {
        return facebook;
    }

    public Evenement facebook(String facebook) {
        this.facebook = facebook;
        return this;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public Evenement twitter(String twitter) {
        this.twitter = twitter;
        return this;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getGplus() {
        return gplus;
    }

    public Evenement gplus(String gplus) {
        this.gplus = gplus;
        return this;
    }

    public void setGplus(String gplus) {
        this.gplus = gplus;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public Evenement linkedin(String linkedin) {
        this.linkedin = linkedin;
        return this;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getInstagram() {
        return instagram;
    }

    public Evenement instagram(String instagram) {
        this.instagram = instagram;
        return this;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getEmail() {
        return email;
    }

    public Evenement email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public Evenement url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOthercontacts() {
        return othercontacts;
    }

    public Evenement othercontacts(String othercontacts) {
        this.othercontacts = othercontacts;
        return this;
    }

    public void setOthercontacts(String othercontacts) {
        this.othercontacts = othercontacts;
    }

    public String getOtherfields() {
        return otherfields;
    }

    public Evenement otherfields(String otherfields) {
        this.otherfields = otherfields;
        return this;
    }

    public void setOtherfields(String otherfields) {
        this.otherfields = otherfields;
    }

    public Instant getCreatedat() {
        return createdat;
    }

    public Evenement createdat(Instant createdat) {
        this.createdat = createdat;
        return this;
    }

    public void setCreatedat(Instant createdat) {
        this.createdat = createdat;
    }

    public Instant getUpdatedat() {
        return updatedat;
    }

    public Evenement updatedat(Instant updatedat) {
        this.updatedat = updatedat;
        return this;
    }

    public void setUpdatedat(Instant updatedat) {
        this.updatedat = updatedat;
    }

    public Files getFiles() {
        return files;
    }

    public Evenement files(Files files) {
        this.files = files;
        return this;
    }

    public void setFiles(Files files) {
        this.files = files;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Evenement currency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Location getLocation() {
        return location;
    }

    public Evenement location(Location location) {
        this.location = location;
        return this;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<Tour> getTours() {
        return tours;
    }

    public Evenement tours(Set<Tour> tours) {
        this.tours = tours;
        return this;
    }

    public Evenement addTour(Tour tour) {
        this.tours.add(tour);
        tour.setEvenement(this);
        return this;
    }

    public Evenement removeTour(Tour tour) {
        this.tours.remove(tour);
        tour.setEvenement(null);
        return this;
    }

    public void setTours(Set<Tour> tours) {
        this.tours = tours;
    }

    public Set<Tourgroup> getTourgroups() {
        return tourgroups;
    }

    public Evenement tourgroups(Set<Tourgroup> tourgroups) {
        this.tourgroups = tourgroups;
        return this;
    }

    public Evenement addTourgroup(Tourgroup tourgroup) {
        this.tourgroups.add(tourgroup);
        tourgroup.setEvenement(this);
        return this;
    }

    public Evenement removeTourgroup(Tourgroup tourgroup) {
        this.tourgroups.remove(tourgroup);
        tourgroup.setEvenement(null);
        return this;
    }

    public void setTourgroups(Set<Tourgroup> tourgroups) {
        this.tourgroups = tourgroups;
    }

    public Set<Tourcategory> getTourcategories() {
        return tourcategories;
    }

    public Evenement tourcategories(Set<Tourcategory> tourcategories) {
        this.tourcategories = tourcategories;
        return this;
    }

    public Evenement addTourcategory(Tourcategory tourcategory) {
        this.tourcategories.add(tourcategory);
        tourcategory.setEvenement(this);
        return this;
    }

    public Evenement removeTourcategory(Tourcategory tourcategory) {
        this.tourcategories.remove(tourcategory);
        tourcategory.setEvenement(null);
        return this;
    }

    public void setTourcategories(Set<Tourcategory> tourcategories) {
        this.tourcategories = tourcategories;
    }

    public Set<Likes> getLikes() {
        return likes;
    }

    public Evenement likes(Set<Likes> likes) {
        this.likes = likes;
        return this;
    }

    public Evenement addLikes(Likes likes) {
        this.likes.add(likes);
        likes.setEvenement(this);
        return this;
    }

    public Evenement removeLikes(Likes likes) {
        this.likes.remove(likes);
        likes.setEvenement(null);
        return this;
    }

    public void setLikes(Set<Likes> likes) {
        this.likes = likes;
    }

    public Set<Ratings> getRatings() {
        return ratings;
    }

    public Evenement ratings(Set<Ratings> ratings) {
        this.ratings = ratings;
        return this;
    }

    public Evenement addRatings(Ratings ratings) {
        this.ratings.add(ratings);
        ratings.setEvenement(this);
        return this;
    }

    public Evenement removeRatings(Ratings ratings) {
        this.ratings.remove(ratings);
        ratings.setEvenement(null);
        return this;
    }

    public void setRatings(Set<Ratings> ratings) {
        this.ratings = ratings;
    }

    public Set<Reviews> getReviews() {
        return reviews;
    }

    public Evenement reviews(Set<Reviews> reviews) {
        this.reviews = reviews;
        return this;
    }

    public Evenement addReviews(Reviews reviews) {
        this.reviews.add(reviews);
        reviews.setEvenement(this);
        return this;
    }

    public Evenement removeReviews(Reviews reviews) {
        this.reviews.remove(reviews);
        reviews.setEvenement(null);
        return this;
    }

    public void setReviews(Set<Reviews> reviews) {
        this.reviews = reviews;
    }

    public Set<Follows> getFollows() {
        return follows;
    }

    public Evenement follows(Set<Follows> follows) {
        this.follows = follows;
        return this;
    }

    public Evenement addFollows(Follows follows) {
        this.follows.add(follows);
        follows.setEvenement(this);
        return this;
    }

    public Evenement removeFollows(Follows follows) {
        this.follows.remove(follows);
        follows.setEvenement(null);
        return this;
    }

    public void setFollows(Set<Follows> follows) {
        this.follows = follows;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public Evenement bookings(Set<Booking> bookings) {
        this.bookings = bookings;
        return this;
    }

    public Evenement addBooking(Booking booking) {
        this.bookings.add(booking);
        booking.setEvenement(this);
        return this;
    }

    public Evenement removeBooking(Booking booking) {
        this.bookings.remove(booking);
        booking.setEvenement(null);
        return this;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    public Set<Paymentaction> getPaymentactions() {
        return paymentactions;
    }

    public Evenement paymentactions(Set<Paymentaction> paymentactions) {
        this.paymentactions = paymentactions;
        return this;
    }

    public Evenement addPaymentaction(Paymentaction paymentaction) {
        this.paymentactions.add(paymentaction);
        paymentaction.setEvenement(this);
        return this;
    }

    public Evenement removePaymentaction(Paymentaction paymentaction) {
        this.paymentactions.remove(paymentaction);
        paymentaction.setEvenement(null);
        return this;
    }

    public void setPaymentactions(Set<Paymentaction> paymentactions) {
        this.paymentactions = paymentactions;
    }

    public Category getCategory() {
        return category;
    }

    public Evenement category(Category category) {
        this.category = category;
        return this;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Product getProduct() {
        return product;
    }

    public Evenement product(Product product) {
        this.product = product;
        return this;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Evenement)) {
            return false;
        }
        return id != null && id.equals(((Evenement) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Evenement{" +
            "id=" + getId() +
            ", categoryid=" + getCategoryid() +
            ", userid=" + getUserid() +
            ", productid=" + getProductid() +
            ", slug='" + getSlug() + "'" +
            ", name='" + getName() + "'" +
            ", islock='" + isIslock() + "'" +
            ", lockdelay='" + getLockdelay() + "'" +
            ", islimited='" + isIslimited() + "'" +
            ", limiteddelay='" + getLimiteddelay() + "'" +
            ", limitedbooking=" + getLimitedbooking() +
            ", startdate='" + getStartdate() + "'" +
            ", enddate='" + getEnddate() + "'" +
            ", about='" + getAbout() + "'" +
            ", title='" + getTitle() + "'" +
            ", tag='" + getTag() + "'" +
            ", tagcolor='" + getTagcolor() + "'" +
            ", postalcode='" + getPostalcode() + "'" +
            ", phones='" + getPhones() + "'" +
            ", website='" + getWebsite() + "'" +
            ", facebook='" + getFacebook() + "'" +
            ", twitter='" + getTwitter() + "'" +
            ", gplus='" + getGplus() + "'" +
            ", linkedin='" + getLinkedin() + "'" +
            ", instagram='" + getInstagram() + "'" +
            ", email='" + getEmail() + "'" +
            ", url='" + getUrl() + "'" +
            ", othercontacts='" + getOthercontacts() + "'" +
            ", otherfields='" + getOtherfields() + "'" +
            ", createdat='" + getCreatedat() + "'" +
            ", updatedat='" + getUpdatedat() + "'" +
            "}";
    }
}
