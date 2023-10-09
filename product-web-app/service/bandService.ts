import axios from 'axios';
import Band from '../model/band.js';
import BandValidator from './bandValidator.js';
import { API } from '../common/constants.js';
import logger from './logger.js';

export default class BandService {
  private bandValidator: BandValidator;

  constructor(bandValidator: BandValidator) {
    this.bandValidator = bandValidator;
  }

  async createBand(band: Band): Promise<Band> {
    const validateError = this.bandValidator.validateBand(band);

    if (validateError) {
      logger.warn(`VALIDATION ERROR: ${validateError}`);
      throw new Error(validateError);
    }

    try {
      const response = await axios.post(API.BANDS, band);

      return response.data;
    } catch (e) {
      logger.error('Could not create band');
      throw new Error('Could not create band');
    }
  }
}
