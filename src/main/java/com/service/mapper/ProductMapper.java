package com.service.mapper;


import com.domain.*;
import com.service.dto.ProductDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Product} and its DTO {@link ProductDTO}.
 */
@Mapper(componentModel = "spring", uses = {FilesMapper.class, ProductdetailsMapper.class, CurrencyMapper.class, LocationMapper.class, CategoryMapper.class})
public interface ProductMapper extends EntityMapper<ProductDTO, Product> {

    @Mapping(source = "files.id", target = "filesId")
    @Mapping(source = "productdetails.id", target = "productdetailsId")
    @Mapping(source = "currency.id", target = "currencyId")
    @Mapping(source = "location.id", target = "locationId")
    @Mapping(source = "category.id", target = "categoryId")
    ProductDTO toDto(Product product);

    @Mapping(source = "filesId", target = "files")
    @Mapping(source = "productdetailsId", target = "productdetails")
    @Mapping(source = "currencyId", target = "currency")
    @Mapping(source = "locationId", target = "location")
    @Mapping(target = "tours", ignore = true)
    @Mapping(target = "removeTour", ignore = true)
    @Mapping(target = "evenements", ignore = true)
    @Mapping(target = "removeEvenement", ignore = true)
    @Mapping(target = "tourgroups", ignore = true)
    @Mapping(target = "removeTourgroup", ignore = true)
    @Mapping(target = "tourcategories", ignore = true)
    @Mapping(target = "removeTourcategory", ignore = true)
    @Mapping(target = "likes", ignore = true)
    @Mapping(target = "removeLikes", ignore = true)
    @Mapping(target = "follows", ignore = true)
    @Mapping(target = "removeFollows", ignore = true)
    @Mapping(target = "ratings", ignore = true)
    @Mapping(target = "removeRatings", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "removeReviews", ignore = true)
    @Mapping(target = "paymentactions", ignore = true)
    @Mapping(target = "removePaymentaction", ignore = true)
    @Mapping(source = "categoryId", target = "category")
    Product toEntity(ProductDTO productDTO);

    default Product fromId(Long id) {
        if (id == null) {
            return null;
        }
        Product product = new Product();
        product.setId(id);
        return product;
    }
}
