import React, { useState } from 'react';
// import './ImageZoom.css'; // Optional CSS file for styling

const ImageZoom = ({ src, alt = "Zoomable image", initialScale = 1 }) => {
  const [scale, setScale] = useState(initialScale);
  
  const zoomIn = () => {
    setScale(prevScale => prevScale * 1.2);
  };
  
  const zoomOut = () => {
    setScale(prevScale => Math.max(prevScale / 1.2, 0.5)); // Don't zoom out too much
  };
  
  const resetZoom = () => {
    setScale(initialScale);
  };
  
  return (
    <div className="image-zoom-container">
      <div className="zoom-controls">
        <button onClick={zoomIn} className="zoom-button">Zoom In</button>
        <button onClick={zoomOut} className="zoom-button">Zoom Out</button>
        <button onClick={resetZoom} className="zoom-button">Reset</button>
      </div>
      
      <div className="image-wrapper">
        <img 
          src={src} 
          alt={alt} 
          style={{ 
            transform: `scale(${scale})`,
            transformOrigin: 'center center',
            transition: 'transform 0.3s ease'
          }} 
          className="zoomable-image"
        />
      </div>
    </div>
  );
};

export default ImageZoom;