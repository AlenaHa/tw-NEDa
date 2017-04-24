import { ModuleWithProviders } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EarthquakeComponent } from './components/earthquakes/earthquake.component';
import { HomeComponent } from './components/home/home.component';
import { LocationComponent } from './components/location/location.component';
import { SuppliesComponent } from './components/supplies/supplies.component';

/** Routes for all our pages in the app **/
export const appRoutes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'home', component: HomeComponent},
  {path: 'earthquakes', component: EarthquakeComponent},
  {path: 'location', component: LocationComponent},
  {path: 'supplies', component: SuppliesComponent}

];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
