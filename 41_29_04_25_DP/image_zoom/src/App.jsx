import React from 'react'
import ImageZoom from './ImageZoom'
import image from './assets/22BD1A051C.jpg'
import './App.css';
import ImageGallery from './ImageGallery';
import TimerComponent from './CountDownTimer';
export default function App() {
  return (
    <>
    {/* <ImageZoom src={image} /> */}
    <TimerComponent />
    {/* <ImageGallery /> */}
    </>
  )
}
