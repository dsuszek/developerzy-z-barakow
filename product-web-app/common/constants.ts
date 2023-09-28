export const API_URL = `${process.env.API_URL}`;

export const API = {
  JOBS: '/api/job-roles',
  GET_ROLE: (id: number) => `/api/job-roles/${id}`,
};
