import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'profile',
        loadChildren: () => import('./profile/profile.module').then(m => m.EBusinessProfileModule)
      },
      {
        path: 'tour',
        loadChildren: () => import('./tour/tour.module').then(m => m.EBusinessTourModule)
      },
      {
        path: 'tourcategory',
        loadChildren: () => import('./tourcategory/tourcategory.module').then(m => m.EBusinessTourcategoryModule)
      },
      {
        path: 'tourgroup',
        loadChildren: () => import('./tourgroup/tourgroup.module').then(m => m.EBusinessTourgroupModule)
      },
      {
        path: 'evenement',
        loadChildren: () => import('./evenement/evenement.module').then(m => m.EBusinessEvenementModule)
      },
      {
        path: 'product',
        loadChildren: () => import('./product/product.module').then(m => m.EBusinessProductModule)
      },
      {
        path: 'productdetails',
        loadChildren: () => import('./productdetails/productdetails.module').then(m => m.EBusinessProductdetailsModule)
      },
      {
        path: 'category',
        loadChildren: () => import('./category/category.module').then(m => m.EBusinessCategoryModule)
      },
      {
        path: 'categorydetails',
        loadChildren: () => import('./categorydetails/categorydetails.module').then(m => m.EBusinessCategorydetailsModule)
      },
      {
        path: 'files',
        loadChildren: () => import('./files/files.module').then(m => m.EBusinessFilesModule)
      },
      {
        path: 'follows',
        loadChildren: () => import('./follows/follows.module').then(m => m.EBusinessFollowsModule)
      },
      {
        path: 'likes',
        loadChildren: () => import('./likes/likes.module').then(m => m.EBusinessLikesModule)
      },
      {
        path: 'ratings',
        loadChildren: () => import('./ratings/ratings.module').then(m => m.EBusinessRatingsModule)
      },
      {
        path: 'reviews',
        loadChildren: () => import('./reviews/reviews.module').then(m => m.EBusinessReviewsModule)
      },
      {
        path: 'paymentmethod',
        loadChildren: () => import('./paymentmethod/paymentmethod.module').then(m => m.EBusinessPaymentmethodModule)
      },
      {
        path: 'paymentcategory',
        loadChildren: () => import('./paymentcategory/paymentcategory.module').then(m => m.EBusinessPaymentcategoryModule)
      },
      {
        path: 'paymentaction',
        loadChildren: () => import('./paymentaction/paymentaction.module').then(m => m.EBusinessPaymentactionModule)
      },
      {
        path: 'cart',
        loadChildren: () => import('./cart/cart.module').then(m => m.EBusinessCartModule)
      },
      {
        path: 'cartproducts',
        loadChildren: () => import('./cartproducts/cartproducts.module').then(m => m.EBusinessCartproductsModule)
      },
      {
        path: 'booking',
        loadChildren: () => import('./booking/booking.module').then(m => m.EBusinessBookingModule)
      },
      {
        path: 'bookingdetails',
        loadChildren: () => import('./bookingdetails/bookingdetails.module').then(m => m.EBusinessBookingdetailsModule)
      },
      {
        path: 'bookingproducts',
        loadChildren: () => import('./bookingproducts/bookingproducts.module').then(m => m.EBusinessBookingproductsModule)
      },
      {
        path: 'bookingview',
        loadChildren: () => import('./bookingview/bookingview.module').then(m => m.EBusinessBookingviewModule)
      },
      {
        path: 'location',
        loadChildren: () => import('./location/location.module').then(m => m.EBusinessLocationModule)
      },
      {
        path: 'shipping',
        loadChildren: () => import('./shipping/shipping.module').then(m => m.EBusinessShippingModule)
      },
      {
        path: 'notification',
        loadChildren: () => import('./notification/notification.module').then(m => m.EBusinessNotificationModule)
      },
      {
        path: 'currency',
        loadChildren: () => import('./currency/currency.module').then(m => m.EBusinessCurrencyModule)
      },
      {
        path: 'cherche',
        loadChildren: () => import('./cherche/cherche.module').then(m => m.EBusinessChercheModule)
      },
      {
        path: 'actus',
        loadChildren: () => import('./actus/actus.module').then(m => m.EBusinessActusModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class EBusinessEntityModule {}
