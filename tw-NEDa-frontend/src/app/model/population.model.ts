/**
 * Created by Cami on 2017-04-21.
 */
/**
 * @author Elena Hardon
 * @date 11/04/2017
 */
export class Population {
  public populationId: number;
  public ageDistribution: string;
  public gender: string;
  public locationId: number;

  constructor(object: any) {
    this.populationId = object.populationId;
    this.ageDistribution = object.ageDistribution;
    this.gender = object.gender;
    this.locationId = object.locationId;
  }
}
