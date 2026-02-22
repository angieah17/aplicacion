import React from 'react';
import { StatusBar } from 'expo-status-bar';
import ListaPreguntasScreen from './src/screens/ListaPreguntasScreen';

export default function App() {
  return (
    <>
      <ListaPreguntasScreen />
      <StatusBar style="auto" />
    </>
  );
}
