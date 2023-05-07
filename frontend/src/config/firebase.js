// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
import { getStorage } from "firebase/storage";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyC449-wlT4F13pYXYq5qr_xdAgxCsM--oI",
  authDomain: "auctionapp-bdab8.firebaseapp.com",
  projectId: "auctionapp-bdab8",
  storageBucket: "auctionapp-bdab8.appspot.com",
  messagingSenderId: "784939104553",
  appId: "1:784939104553:web:c0ec95994284dca21b1f38",
  measurementId: "G-2TNKQ0LQWR"
};

// Initialize Firebase
export const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);
export const storage = getStorage(app);