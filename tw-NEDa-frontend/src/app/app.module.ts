import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { AppComponent } from './app.component';
import { EarthquakeComponent } from './components/earthquakes/earthquake.component';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { MaterialModule } from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';
import { routing } from './app.routing';
import { EarthquakeService } from './services/earthquake.service';
import { EarthquakeDialog } from './components/earthquakes/earthquake.dialog';
import { HomeComponent } from './components/home/home.component';
import { AgmCoreModule } from 'angular2-google-maps/core';
import { LogoComponent } from './components/logo/logo.component';
import { NepalmapComponent } from './components/nepalmap/nepalmap.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { LocationComponent } from './components/location/location.component';
import { Md2DatepickerModule } from 'md2-datepicker';
import { Md2DateUtil } from 'md2-datepicker/datepicker/dateUtil';
import { OngComponent } from './components/ong/ong.component';
import { OngService } from './services/ong.service';
import { OngDialog } from './components/ong/ong.dialog';
import { PopulationComponent } from './components/population/population.component';
import { PopulationService } from './services/population.service';
import { FooterComponent } from './components/footer/footer.component';
import { ChartsModule } from 'ng2-charts';
import { LocationService } from './services/location.service';

@NgModule({
  declarations: [
    AppComponent,
    EarthquakeComponent,
    EarthquakeDialog,
    OngDialog,
    HomeComponent,
    LogoComponent,
    NepalmapComponent,
    NavbarComponent,
    LocationComponent,
    OngComponent,
    PopulationComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    routing,
    FormsModule,
    HttpModule,
    ChartsModule,
    MaterialModule.forRoot(),
    FlexLayoutModule,
    NgxDatatableModule,
    Md2DatepickerModule,


    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBCUrIt3JLF2DlRX6pgDA9ulLZl41uHnTY'
    })
  ],
  providers: [EarthquakeService, Md2DateUtil, OngService, PopulationService, LocationService],
  bootstrap: [AppComponent],
  entryComponents: [EarthquakeDialog, OngDialog]
})
export class AppModule {
}
