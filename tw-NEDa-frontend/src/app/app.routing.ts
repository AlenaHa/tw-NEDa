import { ModuleWithProviders } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { EarthquakeComponent } from './components/earthquakes/earthquake.component';

export const appRoutes: Routes = [
  {path: 'earthquakes', component: EarthquakeComponent}
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
