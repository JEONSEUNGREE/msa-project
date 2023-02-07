import { createSlice } from '@reduxjs/toolkit'
import { LOCAL_URI } from '../constants/constant'
import axios from 'axios';

const initalAuthState = { 
    isLogin: localStorage.getItem('account_token') != null ? true : false 
}

const authSlice = createSlice({
    name: 'auth',
    initialState: initalAuthState,
    reducers: {
        loginHandler(state, actions) {
            axios.post(`${LOCAL_URI}/user-service/signup`, 
            {
                id: actions.payload.id
            }
            ,
            {
                headers: {
                    'X-API-VERSION': 1
                }
            })
            .then(res => 
                console.log(res)
            )
            .catch(err => 
                console.log(err)
            );
            state.isLogin = true
            localStorage.setItem('account_tokgien', actions.payload)
        },
        logoutHandler(state, actions) {
            state.isLogin = false
            localStorage.removeItem('account_token');
        }
    }
});


export default authSlice