/**
 * Created by Cami on 2017-05-19.
 */

export class Location {
  public district: string;
  public locationId: number;
  public municipality: string;

  constructor(object: any) {
    this.district = object.district;
    this.locationId = object.locationId;
    this.municipality = object.municipality;
  }
}
