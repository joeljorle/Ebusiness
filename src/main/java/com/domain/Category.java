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
 * A Category.
 */
@Entity
@Table(name = "category")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "slug", nullable = false)
    private String slug;

    @Column(name = "userid")
    private Integer userid;

    @Column(name = "categoryid")
    private Integer categoryid;

    @Column(name = "name")
    private String name;

    @Column(name = "route")
    private String route;

    @Column(name = "isheader")
    private Boolean isheader;

    @Column(name = "isgroup")
    private Boolean isgroup;

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
    private Categorydetails categorydetails;

    @OneToOne
    @JoinColumn(unique = true)
    private Location location;

    /**
     * category to many relationship.
     */
    @OneToMany(mappedBy = "category")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Product> products = new HashSet<>();

    @OneToMany(mappedBy = "category")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Evenement> evenements = new HashSet<>();

    @OneToMany(mappedBy = "category")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Tour> tours = new HashSet<>();

    @OneToMany(mappedBy = "category")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Likes> likes = new HashSet<>();

    @OneToMany(mappedBy = "category")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Follows> follows = new HashSet<>();

    @OneToMany(mappedBy = "category")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Ratings> ratings = new HashSet<>();

    @OneToMany(mappedBy = "category")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Reviews> reviews = new HashSet<>();

    @OneToMany(mappedBy = "category")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "category")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Tourgroup> tourgroups = new HashSet<>();

    @OneToMany(mappedBy = "category")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Tourcategory> tourcategories = new HashSet<>();

    @OneToMany(mappedBy = "category")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Booking> bookings = new HashSet<>();

    @OneToMany(mappedBy = "category")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Cart> carts = new HashSet<>();

    @OneToMany(mappedBy = "category")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Paymentcategory> paymentcategories = new HashSet<>();

    @OneToMany(mappedBy = "category")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Currency> currencies = new HashSet<>();

    @OneToMany(mappedBy = "category")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Shipping> shippings = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("categories")
    private Category category;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public Category slug(String slug) {
        this.slug = slug;
        return this;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getUserid() {
        return userid;
    }

    public Category userid(Integer userid) {
        this.userid = userid;
        return this;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public Category categoryid(Integer categoryid) {
        this.categoryid = categoryid;
        return this;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public String getName() {
        return name;
    }

    public Category name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoute() {
        return route;
    }

    public Category route(String route) {
        this.route = route;
        return this;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Boolean isIsheader() {
        return isheader;
    }

    public Category isheader(Boolean isheader) {
        this.isheader = isheader;
        return this;
    }

    public void setIsheader(Boolean isheader) {
        this.isheader = isheader;
    }

    public Boolean isIsgroup() {
        return isgroup;
    }

    public Category isgroup(Boolean isgroup) {
        this.isgroup = isgroup;
        return this;
    }

    public void setIsgroup(Boolean isgroup) {
        this.isgroup = isgroup;
    }

    public Boolean isIslock() {
        return islock;
    }

    public Category islock(Boolean islock) {
        this.islock = islock;
        return this;
    }

    public void setIslock(Boolean islock) {
        this.islock = islock;
    }

    public Instant getLockdelay() {
        return lockdelay;
    }

    public Category lockdelay(Instant lockdelay) {
        this.lockdelay = lockdelay;
        return this;
    }

    public void setLockdelay(Instant lockdelay) {
        this.lockdelay = lockdelay;
    }

    public Type getType() {
        return type;
    }

    public Category type(Type type) {
        this.type = type;
        return this;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Instant getCreatedat() {
        return createdat;
    }

    public Category createdat(Instant createdat) {
        this.createdat = createdat;
        return this;
    }

    public void setCreatedat(Instant createdat) {
        this.createdat = createdat;
    }

    public Instant getUpdatedat() {
        return updatedat;
    }

    public Category updatedat(Instant updatedat) {
        this.updatedat = updatedat;
        return this;
    }

    public void setUpdatedat(Instant updatedat) {
        this.updatedat = updatedat;
    }

    public Files getFiles() {
        return files;
    }

    public Category files(Files files) {
        this.files = files;
        return this;
    }

    public void setFiles(Files files) {
        this.files = files;
    }

    public Categorydetails getCategorydetails() {
        return categorydetails;
    }

    public Category categorydetails(Categorydetails categorydetails) {
        this.categorydetails = categorydetails;
        return this;
    }

    public void setCategorydetails(Categorydetails categorydetails) {
        this.categorydetails = categorydetails;
    }

    public Location getLocation() {
        return location;
    }

    public Category location(Location location) {
        this.location = location;
        return this;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public Category products(Set<Product> products) {
        this.products = products;
        return this;
    }

    public Category addProduct(Product product) {
        this.products.add(product);
        product.setCategory(this);
        return this;
    }

    public Category removeProduct(Product product) {
        this.products.remove(product);
        product.setCategory(null);
        return this;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Set<Evenement> getEvenements() {
        return evenements;
    }

    public Category evenements(Set<Evenement> evenements) {
        this.evenements = evenements;
        return this;
    }

    public Category addEvenement(Evenement evenement) {
        this.evenements.add(evenement);
        evenement.setCategory(this);
        return this;
    }

    public Category removeEvenement(Evenement evenement) {
        this.evenements.remove(evenement);
        evenement.setCategory(null);
        return this;
    }

    public void setEvenements(Set<Evenement> evenements) {
        this.evenements = evenements;
    }

    public Set<Tour> getTours() {
        return tours;
    }

    public Category tours(Set<Tour> tours) {
        this.tours = tours;
        return this;
    }

    public Category addTour(Tour tour) {
        this.tours.add(tour);
        tour.setCategory(this);
        return this;
    }

    public Category removeTour(Tour tour) {
        this.tours.remove(tour);
        tour.setCategory(null);
        return this;
    }

    public void setTours(Set<Tour> tours) {
        this.tours = tours;
    }

    public Set<Likes> getLikes() {
        return likes;
    }

    public Category likes(Set<Likes> likes) {
        this.likes = likes;
        return this;
    }

    public Category addLikes(Likes likes) {
        this.likes.add(likes);
        likes.setCategory(this);
        return this;
    }

    public Category removeLikes(Likes likes) {
        this.likes.remove(likes);
        likes.setCategory(null);
        return this;
    }

    public void setLikes(Set<Likes> likes) {
        this.likes = likes;
    }

    public Set<Follows> getFollows() {
        return follows;
    }

    public Category follows(Set<Follows> follows) {
        this.follows = follows;
        return this;
    }

    public Category addFollows(Follows follows) {
        this.follows.add(follows);
        follows.setCategory(this);
        return this;
    }

    public Category removeFollows(Follows follows) {
        this.follows.remove(follows);
        follows.setCategory(null);
        return this;
    }

    public void setFollows(Set<Follows> follows) {
        this.follows = follows;
    }

    public Set<Ratings> getRatings() {
        return ratings;
    }

    public Category ratings(Set<Ratings> ratings) {
        this.ratings = ratings;
        return this;
    }

    public Category addRatings(Ratings ratings) {
        this.ratings.add(ratings);
        ratings.setCategory(this);
        return this;
    }

    public Category removeRatings(Ratings ratings) {
        this.ratings.remove(ratings);
        ratings.setCategory(null);
        return this;
    }

    public void setRatings(Set<Ratings> ratings) {
        this.ratings = ratings;
    }

    public Set<Reviews> getReviews() {
        return reviews;
    }

    public Category reviews(Set<Reviews> reviews) {
        this.reviews = reviews;
        return this;
    }

    public Category addReviews(Reviews reviews) {
        this.reviews.add(reviews);
        reviews.setCategory(this);
        return this;
    }

    public Category removeReviews(Reviews reviews) {
        this.reviews.remove(reviews);
        reviews.setCategory(null);
        return this;
    }

    public void setReviews(Set<Reviews> reviews) {
        this.reviews = reviews;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public Category categories(Set<Category> categories) {
        this.categories = categories;
        return this;
    }

    public Category addCategory(Category category) {
        this.categories.add(category);
        category.setCategory(this);
        return this;
    }

    public Category removeCategory(Category category) {
        this.categories.remove(category);
        category.setCategory(null);
        return this;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Tourgroup> getTourgroups() {
        return tourgroups;
    }

    public Category tourgroups(Set<Tourgroup> tourgroups) {
        this.tourgroups = tourgroups;
        return this;
    }

    public Category addTourgroup(Tourgroup tourgroup) {
        this.tourgroups.add(tourgroup);
        tourgroup.setCategory(this);
        return this;
    }

    public Category removeTourgroup(Tourgroup tourgroup) {
        this.tourgroups.remove(tourgroup);
        tourgroup.setCategory(null);
        return this;
    }

    public void setTourgroups(Set<Tourgroup> tourgroups) {
        this.tourgroups = tourgroups;
    }

    public Set<Tourcategory> getTourcategories() {
        return tourcategories;
    }

    public Category tourcategories(Set<Tourcategory> tourcategories) {
        this.tourcategories = tourcategories;
        return this;
    }

    public Category addTourcategory(Tourcategory tourcategory) {
        this.tourcategories.add(tourcategory);
        tourcategory.setCategory(this);
        return this;
    }

    public Category removeTourcategory(Tourcategory tourcategory) {
        this.tourcategories.remove(tourcategory);
        tourcategory.setCategory(null);
        return this;
    }

    public void setTourcategories(Set<Tourcategory> tourcategories) {
        this.tourcategories = tourcategories;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public Category bookings(Set<Booking> bookings) {
        this.bookings = bookings;
        return this;
    }

    public Category addBooking(Booking booking) {
        this.bookings.add(booking);
        booking.setCategory(this);
        return this;
    }

    public Category removeBooking(Booking booking) {
        this.bookings.remove(booking);
        booking.setCategory(null);
        return this;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    public Set<Cart> getCarts() {
        return carts;
    }

    public Category carts(Set<Cart> carts) {
        this.carts = carts;
        return this;
    }

    public Category addCart(Cart cart) {
        this.carts.add(cart);
        cart.setCategory(this);
        return this;
    }

    public Category removeCart(Cart cart) {
        this.carts.remove(cart);
        cart.setCategory(null);
        return this;
    }

    public void setCarts(Set<Cart> carts) {
        this.carts = carts;
    }

    public Set<Paymentcategory> getPaymentcategories() {
        return paymentcategories;
    }

    public Category paymentcategories(Set<Paymentcategory> paymentcategories) {
        this.paymentcategories = paymentcategories;
        return this;
    }

    public Category addPaymentcategory(Paymentcategory paymentcategory) {
        this.paymentcategories.add(paymentcategory);
        paymentcategory.setCategory(this);
        return this;
    }

    public Category removePaymentcategory(Paymentcategory paymentcategory) {
        this.paymentcategories.remove(paymentcategory);
        paymentcategory.setCategory(null);
        return this;
    }

    public void setPaymentcategories(Set<Paymentcategory> paymentcategories) {
        this.paymentcategories = paymentcategories;
    }

    public Set<Currency> getCurrencies() {
        return currencies;
    }

    public Category currencies(Set<Currency> currencies) {
        this.currencies = currencies;
        return this;
    }

    public Category addCurrency(Currency currency) {
        this.currencies.add(currency);
        currency.setCategory(this);
        return this;
    }

    public Category removeCurrency(Currency currency) {
        this.currencies.remove(currency);
        currency.setCategory(null);
        return this;
    }

    public void setCurrencies(Set<Currency> currencies) {
        this.currencies = currencies;
    }

    public Set<Shipping> getShippings() {
        return shippings;
    }

    public Category shippings(Set<Shipping> shippings) {
        this.shippings = shippings;
        return this;
    }

    public Category addShipping(Shipping shipping) {
        this.shippings.add(shipping);
        shipping.setCategory(this);
        return this;
    }

    public Category removeShipping(Shipping shipping) {
        this.shippings.remove(shipping);
        shipping.setCategory(null);
        return this;
    }

    public void setShippings(Set<Shipping> shippings) {
        this.shippings = shippings;
    }

    public Category getCategory() {
        return category;
    }

    public Category category(Category category) {
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
        if (!(o instanceof Category)) {
            return false;
        }
        return id != null && id.equals(((Category) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Category{" +
            "id=" + getId() +
            ", slug='" + getSlug() + "'" +
            ", userid=" + getUserid() +
            ", categoryid=" + getCategoryid() +
            ", name='" + getName() + "'" +
            ", route='" + getRoute() + "'" +
            ", isheader='" + isIsheader() + "'" +
            ", isgroup='" + isIsgroup() + "'" +
            ", islock='" + isIslock() + "'" +
            ", lockdelay='" + getLockdelay() + "'" +
            ", type='" + getType() + "'" +
            ", createdat='" + getCreatedat() + "'" +
            ", updatedat='" + getUpdatedat() + "'" +
            "}";
    }
}
