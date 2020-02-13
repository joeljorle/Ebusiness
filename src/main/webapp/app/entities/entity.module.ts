import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'profile',
        loadChildren: () => import('./profile/profile.module').then(m => m.EbusinessProfileModule)
      },
      {
        path: 'tour',
        loadChildren: () => import('./tour/tour.module').then(m => m.EbusinessTourModule)
      },
      {
        path: 'tourcategory',
        loadChildren: () => import('./tourcategory/tourcategory.module').then(m => m.EbusinessTourcategoryModule)
      },
      {
        path: 'tourgroup',
        loadChildren: () => import('./tourgroup/tourgroup.module').then(m => m.EbusinessTourgroupModule)
      },
      {
        path: 'evenement',
        loadChildren: () => import('./evenement/evenement.module').then(m => m.EbusinessEvenementModule)
      },
      {
        path: 'product',
        loadChildren: () => import('./product/product.module').then(m => m.EbusinessProductModule)
      },
      {
        path: 'productdetails',
        loadChildren: () => import('./productdetails/productdetails.module').then(m => m.EbusinessProductdetailsModule)
      },
      {
        path: 'category',
        loadChildren: () => import('./category/category.module').then(m => m.EbusinessCategoryModule)
      },
      {
        path: 'categorydetails',
        loadChildren: () => import('./categorydetails/categorydetails.module').then(m => m.EbusinessCategorydetailsModule)
      },
      {
        path: 'files',
        loadChildren: () => import('./files/files.module').then(m => m.EbusinessFilesModule)
      },
      {
        path: 'follows',
        loadChildren: () => import('./follows/follows.module').then(m => m.EbusinessFollowsModule)
      },
      {
        path: 'likes',
        loadChildren: () => import('./likes/likes.module').then(m => m.EbusinessLikesModule)
      },
      {
        path: 'ratings',
        loadChildren: () => import('./ratings/ratings.module').then(m => m.EbusinessRatingsModule)
      },
      {
        path: 'reviews',
        loadChildren: () => import('./reviews/reviews.module').then(m => m.EbusinessReviewsModule)
      },
      {
        path: 'paymentmethod',
        loadChildren: () => import('./paymentmethod/paymentmethod.module').then(m => m.EbusinessPaymentmethodModule)
      },
      {
        path: 'paymentcategory',
        loadChildren: () => import('./paymentcategory/paymentcategory.module').then(m => m.EbusinessPaymentcategoryModule)
      },
      {
        path: 'paymentaction',
        loadChildren: () => import('./paymentaction/paymentaction.module').then(m => m.EbusinessPaymentactionModule)
      },
      {
        path: 'cart',
        loadChildren: () => import('./cart/cart.module').then(m => m.EbusinessCartModule)
      },
      {
        path: 'cartproducts',
        loadChildren: () => import('./cartproducts/cartproducts.module').then(m => m.EbusinessCartproductsModule)
      },
      {
        path: 'booking',
        loadChildren: () => import('./booking/booking.module').then(m => m.EbusinessBookingModule)
      },
      {
        path: 'bookingdetails',
        loadChildren: () => import('./bookingdetails/bookingdetails.module').then(m => m.EbusinessBookingdetailsModule)
      },
      {
        path: 'bookingproducts',
        loadChildren: () => import('./bookingproducts/bookingproducts.module').then(m => m.EbusinessBookingproductsModule)
      },
      {
        path: 'bookingview',
        loadChildren: () => import('./bookingview/bookingview.module').then(m => m.EbusinessBookingviewModule)
      },
      {
        path: 'location',
        loadChildren: () => import('./location/location.module').then(m => m.EbusinessLocationModule)
      },
      {
        path: 'shipping',
        loadChildren: () => import('./shipping/shipping.module').then(m => m.EbusinessShippingModule)
      },
      {
        path: 'notification',
        loadChildren: () => import('./notification/notification.module').then(m => m.EbusinessNotificationModule)
      },
      {
        path: 'currency',
        loadChildren: () => import('./currency/currency.module').then(m => m.EbusinessCurrencyModule)
      },
      {
        path: 'cherche',
        loadChildren: () => import('./cherche/cherche.module').then(m => m.EbusinessChercheModule)
      },
      {
        path: 'actus',
        loadChildren: () => import('./actus/actus.module').then(m => m.EbusinessActusModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class EbusinessEntityModule {}
