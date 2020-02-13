package com.service.mapper;


import com.domain.*;
import com.service.dto.CategoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Category} and its DTO {@link CategoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {FilesMapper.class, CategorydetailsMapper.class, LocationMapper.class})
public interface CategoryMapper extends EntityMapper<CategoryDTO, Category> {

    @Mapping(source = "files.id", target = "filesId")
    @Mapping(source = "categorydetails.id", target = "categorydetailsId")
    @Mapping(source = "location.id", target = "locationId")
    @Mapping(source = "category.id", target = "categoryId")
    CategoryDTO toDto(Category category);

    @Mapping(source = "filesId", target = "files")
    @Mapping(source = "categorydetailsId", target = "categorydetails")
    @Mapping(source = "locationId", target = "location")
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "removeProduct", ignore = true)
    @Mapping(target = "evenements", ignore = true)
    @Mapping(target = "removeEvenement", ignore = true)
    @Mapping(target = "tours", ignore = true)
    @Mapping(target = "removeTour", ignore = true)
    @Mapping(target = "likes", ignore = true)
    @Mapping(target = "removeLikes", ignore = true)
    @Mapping(target = "follows", ignore = true)
    @Mapping(target = "removeFollows", ignore = true)
    @Mapping(target = "ratings", ignore = true)
    @Mapping(target = "removeRatings", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "removeReviews", ignore = true)
    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "removeCategory", ignore = true)
    @Mapping(target = "tourgroups", ignore = true)
    @Mapping(target = "removeTourgroup", ignore = true)
    @Mapping(target = "tourcategories", ignore = true)
    @Mapping(target = "removeTourcategory", ignore = true)
    @Mapping(target = "bookings", ignore = true)
    @Mapping(target = "removeBooking", ignore = true)
    @Mapping(target = "carts", ignore = true)
    @Mapping(target = "removeCart", ignore = true)
    @Mapping(target = "paymentcategories", ignore = true)
    @Mapping(target = "removePaymentcategory", ignore = true)
    @Mapping(target = "currencies", ignore = true)
    @Mapping(target = "removeCurrency", ignore = true)
    @Mapping(target = "shippings", ignore = true)
    @Mapping(target = "removeShipping", ignore = true)
    @Mapping(source = "categoryId", target = "category")
    Category toEntity(CategoryDTO categoryDTO);

    default Category fromId(Long id) {
        if (id == null) {
            return null;
        }
        Category category = new Category();
        category.setId(id);
        return category;
    }
}
