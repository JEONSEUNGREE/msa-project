// import React, { useState } from 'react'

// const AuthContext = React.createContext({
//     token: '',
//     isLoggedIn: false,
//     login: (token) => {},
//     logout: () => {}
// });


// export const AuthContextProvier = (props) => {
//     const initalAuthState = localStorage.getItem('account_token');
//     const [token, setToken] = useState(initalAuthState);

//     const userIsLoggedIn = !!token;

//     const loginHandler = (token) => {
//         setToken(token);
//         localStorage.setItem('account_token', token)
//     };

//     const logoutHandler = () => {
//         setToken(null);
//         localStorage.removeItem('token');
//     };

//     const contextValue = {
//         token: token,
//         isLoggedIn: userIsLoggedIn,
//         login: loginHandler,
//         logout: logoutHandler
//     };

//     return (
//         <AuthContext.Provider value={contextValue}>
//             {props.children}
//         </AuthContext.Provider>
//     )
// }

// export default AuthContext;