export const API_URL = `${process.env.API_URL}`;
export const JWT_SECRET = `${process.env.JWT_SECRET}`;

export const API = {
  PRODUCTS: '/api/products',
  GET_PRODUCT: (id: number) => `/api/products/${id}`,
};
