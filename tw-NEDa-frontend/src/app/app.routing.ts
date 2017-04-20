import {ModuleWithProviders} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {EarthquakeComponent} from "./components/earthquakes/earthquake.component";
import {HomeComponent} from "./components/home/home.component";

export const appRoutes: Routes = [

  {path: '', component: HomeComponent},
  {path: 'home', component: HomeComponent},
  {path: 'earthquakes', component: EarthquakeComponent}

];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
