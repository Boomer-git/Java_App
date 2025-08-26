import static spark.Spark.*;

public class CalculatorServer {
    public static void main(String[] args) {
        port(80); // Run on port 80

        // Serve the HTML UI
        get("/", (req, res) -> {
            return """
                <!DOCTYPE html>
                <html>
                <head>
                    <title>Web Calculator</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            display: flex;
                            justify-content: center;
                            align-items: center;
                            height: 100vh;
                            background: linear-gradient(135deg, #74ebd5 0%, #ACB6E5 100%);
                        }
                        .calculator {
                            background: white;
                            padding: 30px;
                            border-radius: 15px;
                            box-shadow: 0 8px 16px rgba(0,0,0,0.2);
                            text-align: center;
                            width: 300px;
                        }
                        input, select, button {
                            margin: 8px 0;
                            padding: 10px;
                            width: 100%;
                            border-radius: 8px;
                            border: 1px solid #ccc;
                            font-size: 16px;
                        }
                        button {
                            background-color: #4CAF50;
                            color: white;
                            border: none;
                            cursor: pointer;
                            transition: 0.3s;
                        }
                        button:hover {
                            background-color: #45a049;
                        }
                        .result {
                            margin-top: 15px;
                            font-size: 18px;
                            font-weight: bold;
                            color: #333;
                        }
                    </style>
                </head>
                <body>
                    <div class="calculator">
                        <h2>Calculator</h2>
                        <form action="/calculate" method="post">
                            <input type="number" name="num1" placeholder="Enter first number" required>
                            <input type="number" name="num2" placeholder="Enter second number" required>
                            <select name="op">
                                <option value="add">Addition (+)</option>
                                <option value="sub">Subtraction (-)</option>
                                <option value="mul">Multiplication (×)</option>
                                <option value="div">Division (÷)</option>
                            </select>
                            <button type="submit">Calculate</button>
                        </form>
                        <div class="result">%s</div>
                    </div>
                </body>
                </html>
            """.formatted(req.queryParams("result") != null ? req.queryParams("result") : "");
        });

        // Handle calculation
        post("/calculate", (req, res) -> {
            double num1 = Double.parseDouble(req.queryParams("num1"));
            double num2 = Double.parseDouble(req.queryParams("num2"));
            String op = req.queryParams("op");
            double result = 0;

            switch (op) {
                case "add": result = num1 + num2; break;
                case "sub": result = num1 - num2; break;
                case "mul": result = num1 * num2; break;
                case "div": result = num2 != 0 ? num1 / num2 : 0; break;
            }

            // Redirect back with result
            res.redirect("/?result=" + result);
            return null;
        });
    }
}
