import Band from '../model/band.js';

export default class BandValidator {
  validateBand(band: Band) {
    if (band.level < 0) {
      return 'Level of band should be greater than 0!';
    }

    if (band.level > 10) {
      return 'Level of band should be smaller than 10!';
    }

    return null;
  }
}
