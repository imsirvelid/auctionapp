// Import the functions you need from the SDKs you need
import {initializeApp} from "firebase/app";
import {getAnalytics} from "firebase/analytics";
import {getStorage} from "firebase/storage";
import {getFirebaseConfig} from "api/Firebase";

export let app;
export let analytics;
export let storage;

export const setupFirebaseConfig = async () => {
  const firebaseConfig = await getFirebaseConfig();
  app = initializeApp(firebaseConfig);
  analytics = getAnalytics(app);
  storage = getStorage(app);
};
