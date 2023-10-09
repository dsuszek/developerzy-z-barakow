export const API_URL = `${process.env.API_URL}`;

export const API = {
  JOBS: '/api/job-roles',
  GET_ROLE: (id: number) => `/api/job-roles/${id}`,
  PRODUCTS: '/api/products',
  GET_PRODUCT: (id: number) => `/api/products/${id}`,
  JOB_ROLES: '/api/admin/job-roles',
  REGISTRATION: '/api/auth/register',
  BANDS: '/api/admin/band',
};
