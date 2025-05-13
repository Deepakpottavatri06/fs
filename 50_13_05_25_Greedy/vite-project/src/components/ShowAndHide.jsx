import React from 'react'
import { useState } from 'react'
export default function ShowAndHide() {
     const [show, setShow] = useState(false);
    const [show2, setShow2] = useState(false);
    const handleClick = () => {
        setShow(!show);
    };

    const handleClick2 = () => {
        setShow2(!show2);
    }

    return (
        <div>
            <button onClick={handleClick}>
                {show ? 'Hide' : 'Show'} Message
            </button>
            <button onClick={handleClick2}>
                {show2 ? 'Hide' : 'Show'} Message
            </button>
            {(show && show2) && (
                <div>
                    <h1>Welcome to my page</h1>
                </div>
            )}
        </div>

    )
}
