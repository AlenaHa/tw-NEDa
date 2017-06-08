export class OngDetails {
  public district: string;
  public ongName: string;
  public activityType: string;
  public activitySubtype: string;
  public supplyName: string;


  constructor(object: any) {
    this.district = object.district;
    this.ongName = object.ongName;
    this.supplyName = object.supplyName;
    this.activityType = object.activityType;
    this.activitySubtype = object.activitySubtype;
  }
}
