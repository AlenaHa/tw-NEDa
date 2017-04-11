/**
 * @author Elena Hardon
 * @date 11/04/2017
 */
export class Earthquake {
  public earthquakeId: number;
  public localizationId: number;
  public latitude: number;
  public longitude: number;
  public depth: number;
  public magnitude: number;
  public happenedOn: Date;

  constructor(object: any) {
    this.earthquakeId = object.earthquakeId;
    this.localizationId = object.localizationId;
    this.latitude = object.latitude;
    this.longitude = object.longitude;
    this.depth = object.depth;
    this.magnitude = object.magnitude;
    this.happenedOn = new Date(object.happenedOn);
  }
}
