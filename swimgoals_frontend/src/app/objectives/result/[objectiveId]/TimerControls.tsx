type TimerControlsProps = {
    isRunning: boolean;
    onStart: () => void;
    onStop: () => void;
};

const TimerControls = ({ isRunning, onStart, onStop }: TimerControlsProps) => {
    return (
        <button onClick={isRunning ? onStop : onStart}>
            {isRunning ? "Stop" : "Start"}
        </button>
    );
};


export default TimerControls;
