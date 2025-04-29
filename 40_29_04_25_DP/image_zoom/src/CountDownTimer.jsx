import React, { useState, useEffect } from 'react';

const TimerComponent = () => {
  const [currentTime, setCurrentTime] = useState('');
  const [inputMinutes, setInputMinutes] = useState('');
  const [remainingSeconds, setRemainingSeconds] = useState(0);
  const [isActive, setIsActive] = useState(false);
  const [wasPaused, setWasPaused] = useState(false);

  // Update current time every second
  useEffect(() => {
    const updateTime = () => {
      const now = new Date();
      const hours = String(now.getHours()).padStart(2, '0');
      const minutes = String(now.getMinutes()).padStart(2, '0');
      const seconds = String(now.getSeconds()).padStart(2, '0');
      setCurrentTime(`${hours}-${minutes}-${seconds}`);
    };

    updateTime();
    const intervalId = setInterval(updateTime, 1000);

    return () => clearInterval(intervalId);
  }, []);

  // Update inputMinutes when remainingSeconds changes
  useEffect(() => {
    if (isActive || wasPaused) {
      const remainingMinutes = Math.ceil(remainingSeconds / 60);
      setInputMinutes(String(remainingMinutes));
    }
  }, [remainingSeconds, isActive, wasPaused]);

  // Timer logic
  useEffect(() => {
    let intervalId;

    if (isActive && remainingSeconds > 0) {
      intervalId = setInterval(() => {
        setRemainingSeconds(prev => prev - 1);
      }, 1000);
    } else if (isActive && remainingSeconds === 0) {
      setIsActive(false);
    }

    return () => clearInterval(intervalId);
  }, [isActive, remainingSeconds]);

  const handleStart = () => {
    if (!isActive) {
      if (!wasPaused) {
        // Starting fresh timer
        const minutes = parseInt(inputMinutes, 10) || 0;
        setRemainingSeconds(minutes * 60);
      }
      setIsActive(true);
      setWasPaused(false);
    }
  };

  const handleStop = () => {
    setIsActive(false);
    setWasPaused(true);
  };

  const handleReset = () => {
    setIsActive(false);
    setWasPaused(false);
    setInputMinutes('');
    setRemainingSeconds(0);
  };

  const handleInputChange = (e) => {
    const value = e.target.value;
    if (!isActive && !wasPaused) {
      setInputMinutes(value);
      setRemainingSeconds(0);
    }
  };

  const formatTime = (seconds) => {
    const mins = Math.floor(seconds / 60);
    const secs = seconds % 60;
    return `${String(mins).padStart(2, '0')}:${String(secs).padStart(2, '0')}`;
  };

  return (
    <div style={{ padding: '20px', fontFamily: 'Arial, sans-serif' }}>
      <h2>Current Time: {currentTime}</h2>
      
      <div style={{ margin: '20px 0' }}>
        <label htmlFor="minutesInput">Set Timer (minutes): </label>
        <input
          id="minutesInput"
          type="number"
          value={inputMinutes}
          onChange={handleInputChange}
          min="1"
          disabled={isActive}
          style={{ padding: '5px', marginRight: '10px' }}
        />
      </div>
      
      {/* <div style={{ margin: '20px 0', fontSize: '24px' }}>
        Remaining Time: {formatTime(remainingSeconds)}
      </div> */}
      
      <div>
        <button
          onClick={handleStart}
          disabled={isActive || (!inputMinutes && !wasPaused)}
          style={{
            padding: '8px 16px',
            marginRight: '10px',
            backgroundColor: '#4CAF50',
            color: 'white',
            border: 'none',
            borderRadius: '4px',
            cursor: 'pointer'
          }}
        >
          Start
        </button>
        <button
          onClick={handleStop}
          disabled={!isActive}
          style={{
            padding: '8px 16px',
            marginRight: '10px',
            backgroundColor: '#f44336',
            color: 'white',
            border: 'none',
            borderRadius: '4px',
            cursor: 'pointer'
          }}
        >
          Stop
        </button>
        <button
          onClick={handleReset}
          style={{
            padding: '8px 16px',
            backgroundColor: '#2196F3',
            color: 'white',
            border: 'none',
            borderRadius: '4px',
            cursor: 'pointer'
          }}
        >
          Reset
        </button>
      </div>
    </div>
  );
};

export default TimerComponent;