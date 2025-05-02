import React, { useState, useEffect } from 'react';
import Lightbox from 'yet-another-react-lightbox';
import 'yet-another-react-lightbox/styles.css';
import './ImageGallery.css';

const ImageGallery = () => {
    // Using different images to verify they load
    const images = [
        { src: 'https://images.unsplash.com/photo-1542291026-7eec264c27ff?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80' },
        { src: 'https://images.unsplash.com/photo-1505740420928-5e560c06d30e?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80' },
        { src: 'https://images.unsplash.com/photo-1523275335684-37898b6baf30?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80' },
        { src: 'https://images.unsplash.com/photo-1491553895911-0055eca6402d?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80' },
        { src: 'https://images.unsplash.com/photo-1546868871-7041f2a55e12?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80' },
        { src: 'https://images.unsplash.com/photo-1572635196237-14b3f281503f?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80' }
    ];

    const [open, setOpen] = useState(false);
    const [index, setIndex] = useState(0);
    const [zoom, setZoom] = useState(1);


    // Debugging function
    const handleImageClick = (idx) => {
        console.log('Image clicked, index:', idx); // Verify click works
        setIndex(idx);
        setOpen(true);
        console.log('Lightbox should open now'); // Verify state change
    };

    useEffect(() => {
        const handleKeyDown = (e) => {
            if (!open) return;

            if (e.key === 'ArrowUp') {
                setZoom((z) => z + 0.1);
            } else if (e.key === 'ArrowDown') {
                setZoom((z) => Math.max(0.1, z - 0.1));
            }
            
        };

        window.addEventListener('keydown', handleKeyDown);
        return () => window.removeEventListener('keydown', handleKeyDown);
    }, [open]);

    return (
        <div className="gallery-container">
            <h2>Image Gallery</h2>

            <div className="image-grid">
                {images.map((img, idx) => (
                    <div
                        key={idx}
                        className="image-item"
                        onClick={() => handleImageClick(idx)}
                    >
                        <img
                            src={img.src}
                            alt={`Gallery item ${idx + 1}`}
                            className="gallery-image"
                            onError={(e) => {
                                console.error('Image failed to load:', img.src);
                                e.target.src = 'https://via.placeholder.com/300x300?text=Image+Not+Found';
                            }}
                        />
                    </div>
                ))}
            </div>

            {open && (
                <Lightbox
                    open={open}
                    close={() => {
                        console.log('Closing lightbox'); // Verify close action
                        setOpen(false);
                    }}
                    index={index}
                    slides={images}
                    render={{

                        slide: ({ slide }) => (
                            <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100%' }}>
                                <img
                                    src={slide.src}
                                    alt=""
                                    style={{
                                        transform: `scale(${zoom})`,
                                        transition: 'transform 0.2s ease',
                                        maxWidth: '100%',
                                        maxHeight: '100%',
                                        objectFit: 'contain'
                                    }}
                                />

                            </div>
                        )
                    }}

                />
            )}
        </div>
    );
};

export default ImageGallery;