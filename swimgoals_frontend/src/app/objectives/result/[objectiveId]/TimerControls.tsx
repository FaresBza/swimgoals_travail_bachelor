const TimerControls = ({
    isRunning,
    onStart,
    onStop,
    onReset
}: {
    isRunning: boolean;
    onStart: () => void;
    onStop: () => void;
    onReset: () => void;
}) => {
    return (
        <div className="timer-controls">
            <button
                className="timer-button"
                onClick={isRunning ? onStop : onStart}
            >
                {isRunning ? "Stop" : "Start"}
            </button>
            <button
                className="timer-button reset-button"
                onClick={onReset}
                disabled={isRunning}
            >
                Reset
            </button>
        </div>
    );
};

export default TimerControls;
