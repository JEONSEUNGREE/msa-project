import { configureStore } from '@reduxjs/toolkit';

import authState from './authState';


const store = configureStore({
    reducer: { auth: authState.reducer }
});

export const authActions = authState.actions;

export default store;


