/**
 * Created by Cami on 2017-04-20.
 */

export class Ong {
  public ongId: number;
  public ongName: string;
  public activityType: string;
  public subactivityType: string;
  public locationListOng: string;

  constructor(object: any) {
    this.ongId = object.ongId;
    this.ongName = object.ongName;
    this.activityType = object.activityType;
    this.subactivityType = object.subactivityType;
    this.locationListOng = object.locationListOng;
  }
}
