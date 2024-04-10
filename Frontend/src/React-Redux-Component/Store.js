import { configureStore } from "@reduxjs/toolkit";
import credentialsSlice from "./credentials-slice";


const store = configureStore({
    reducer: {
        credential: credentialsSlice.reducer,
    }
});

export default store;