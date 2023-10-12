import Capability from '../model/capability.js';

export default class CapabilityValidator {
  validateCapability(capability: Capability) {
    if (capability.capabilityName.length > 50) {
      return 'Capability name greater than 50 characters';
    }

    if (capability.leadName.length > 50) {
      return 'Lead name greater than 50 characters';
    }

    if (capability.capabilityLeadPicture.length > 2147483647 - 1) {
      return 'Picture size too large';
    }
    if (capability.message.length > 3000) {
      return 'Message too long';
    }

    return null;
  }
}
