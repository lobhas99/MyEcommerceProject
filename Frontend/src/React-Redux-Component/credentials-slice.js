import { createSlice } from "@reduxjs/toolkit";

const credentialsSlice = createSlice({
    name: "credential",
    initialState:
    {
        credentials: {
            'firstName': '',
            'lastName': '',
            'email': '',
            'mobileNo': '',
            'dob': '',
            'address': '',
         
        },
        isCustomer: false,
        isAdmin: false
    },

    reducers: {
        setCredentials(state, action) {
            state.credentials = { ...action.payload };
        },

        setCustomerStatus(state, action) {
            state.isCustomer = action.payload;
        },
        setAdminStatus(state, action) {
            state.isAdmin = action.payload;
        }
    }
})

export const credentialsActions = credentialsSlice.actions;

export default credentialsSlice;