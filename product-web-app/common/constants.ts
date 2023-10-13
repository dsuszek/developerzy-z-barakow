export const API_URL = `${process.env.API_URL}`;
export const JWT_SECRET = `${process.env.JWT_SECRET}`;

export const API = {
  PRODUCTS: '/api/products',
  GET_PRODUCT: (id: number) => `/api/products/${id}`,
  JOB_ROLES: '/api/admin/job-roles',
  JOB_ROLE: '/api/admin/job-role',
  REGISTRATION: '/api/auth/register',
  JOBS: '/api/job-roles',
  GET_ROLE: (id: number) => `/api/job-roles/${id}`,
  GET_CAPABILITIES: '/api/capabilities',
  BANDS: '/api/admin/band',
  POST_CAPABILITES: '/api/capabilities',
};
