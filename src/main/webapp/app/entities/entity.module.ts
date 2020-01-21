import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'profile',
        loadChildren: () => import('./profile/profile.module').then(m => m.EbusinesProfileModule)
      },
      {
        path: 'tour',
        loadChildren: () => import('./tour/tour.module').then(m => m.EbusinesTourModule)
      },
      {
        path: 'tourcategory',
        loadChildren: () => import('./tourcategory/tourcategory.module').then(m => m.EbusinesTourcategoryModule)
      },
      {
        path: 'tourgroup',
        loadChildren: () => import('./tourgroup/tourgroup.module').then(m => m.EbusinesTourgroupModule)
      },
      {
        path: 'evenement',
        loadChildren: () => import('./evenement/evenement.module').then(m => m.EbusinesEvenementModule)
      },
      {
        path: 'product',
        loadChildren: () => import('./product/product.module').then(m => m.EbusinesProductModule)
      },
      {
        path: 'productdetails',
        loadChildren: () => import('./productdetails/productdetails.module').then(m => m.EbusinesProductdetailsModule)
      },
      {
        path: 'category',
        loadChildren: () => import('./category/category.module').then(m => m.EbusinesCategoryModule)
      },
      {
        path: 'categorydetails',
        loadChildren: () => import('./categorydetails/categorydetails.module').then(m => m.EbusinesCategorydetailsModule)
      },
      {
        path: 'files',
        loadChildren: () => import('./files/files.module').then(m => m.EbusinesFilesModule)
      },
      {
        path: 'follows',
        loadChildren: () => import('./follows/follows.module').then(m => m.EbusinesFollowsModule)
      },
      {
        path: 'likes',
        loadChildren: () => import('./likes/likes.module').then(m => m.EbusinesLikesModule)
      },
      {
        path: 'ratings',
        loadChildren: () => import('./ratings/ratings.module').then(m => m.EbusinesRatingsModule)
      },
      {
        path: 'reviews',
        loadChildren: () => import('./reviews/reviews.module').then(m => m.EbusinesReviewsModule)
      },
      {
        path: 'paymentmethod',
        loadChildren: () => import('./paymentmethod/paymentmethod.module').then(m => m.EbusinesPaymentmethodModule)
      },
      {
        path: 'paymentcategory',
        loadChildren: () => import('./paymentcategory/paymentcategory.module').then(m => m.EbusinesPaymentcategoryModule)
      },
      {
        path: 'paymentaction',
        loadChildren: () => import('./paymentaction/paymentaction.module').then(m => m.EbusinesPaymentactionModule)
      },
      {
        path: 'cart',
        loadChildren: () => import('./cart/cart.module').then(m => m.EbusinesCartModule)
      },
      {
        path: 'cartproducts',
        loadChildren: () => import('./cartproducts/cartproducts.module').then(m => m.EbusinesCartproductsModule)
      },
      {
        path: 'booking',
        loadChildren: () => import('./booking/booking.module').then(m => m.EbusinesBookingModule)
      },
      {
        path: 'bookingdetails',
        loadChildren: () => import('./bookingdetails/bookingdetails.module').then(m => m.EbusinesBookingdetailsModule)
      },
      {
        path: 'bookingproducts',
        loadChildren: () => import('./bookingproducts/bookingproducts.module').then(m => m.EbusinesBookingproductsModule)
      },
      {
        path: 'bookingview',
        loadChildren: () => import('./bookingview/bookingview.module').then(m => m.EbusinesBookingviewModule)
      },
      {
        path: 'location',
        loadChildren: () => import('./location/location.module').then(m => m.EbusinesLocationModule)
      },
      {
        path: 'shipping',
        loadChildren: () => import('./shipping/shipping.module').then(m => m.EbusinesShippingModule)
      },
      {
        path: 'notification',
        loadChildren: () => import('./notification/notification.module').then(m => m.EbusinesNotificationModule)
      },
      {
        path: 'currency',
        loadChildren: () => import('./currency/currency.module').then(m => m.EbusinesCurrencyModule)
      },
      {
        path: 'cherche',
        loadChildren: () => import('./cherche/cherche.module').then(m => m.EbusinesChercheModule)
      },
      {
        path: 'actus',
        loadChildren: () => import('./actus/actus.module').then(m => m.EbusinesActusModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class EbusinesEntityModule {}
