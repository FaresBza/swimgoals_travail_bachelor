import "@/app/styles/Timer.scss";

const TimerControls = ({ isRunning, onStart, onStop }: {
    isRunning: boolean;
    onStart: () => void;
    onStop: () => void;
}) => {
    return (
        <button
            className="timer-button"
            onClick={isRunning ? onStop : onStart}
        >
            {isRunning ? "Stop" : "Start"}
        </button>
    );
};

export default TimerControls;
