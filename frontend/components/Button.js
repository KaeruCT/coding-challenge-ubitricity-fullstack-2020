function Button({ children, onClick }) {
    return (
        <button onClick={onClick}>
            {children}
            <style jsx>{`
                button {
                    border: 0;
                    background: #8ca;
                    color: #124;
                    font-weight: bold;
                    text-transform: uppercase;
                    text-align: center;
                    padding: 10px 8px;
                    font-size: 12px;
                }
                button:hover {
                    background: #aec;
                    box-shadow: rgba(255, 255, 255, 0.2) 0 0 10px;
                }
            `}</style>
        </button>
    )
}

export default Button;