function Cell({ value, onClick, disabled }) {

    return (
        <button
            onClick={onClick}
            disabled={disabled}
            style={{
                width: "80px",
                height: "80px",
                fontSize: "2rem",
                cursor: disabled ? "not-allowed" : "pointer"
            }}
        >
            {value === "EMPTY" ? "" : value}
        </button>
    );
}

export default Cell;