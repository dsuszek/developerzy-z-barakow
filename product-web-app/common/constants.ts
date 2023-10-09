export const API_URL = `${process.env.API_URL}`;

export const API = {
  PRODUCTS: '/api/products',
  GET_PRODUCT: (id: number) => `/api/products/${id}`,
  JOB_ROLES: '/api/admin/job-roles',
  REGISTRATION: '/api/auth/register',
  JOBS: '/api/job-roles',
  GET_ROLE: (id: number) => `/api/job-roles/${id}`,
  GET_CAPABILITIES: '/api/capabilities',
  POST_CAPABILITES: '/api/capabilities',
};
