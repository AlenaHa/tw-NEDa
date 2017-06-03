export class CompleteEarthquake {
  public latitude: number;
  public longitude: number;
  public depth: number;
  public magnitude: number;
  public happenedOn: Date;
  public district: string;
  public municipality: string;

  constructor(object: any) {
    this.latitude = object.latitude;
    this.longitude = object.longitude;
    this.depth = object.depth;
    this.magnitude = object.magnitude;
    this.happenedOn = new Date(object.happenedOn);
    this.district = object.district;
    this.municipality = object.municipality;
  }
}
