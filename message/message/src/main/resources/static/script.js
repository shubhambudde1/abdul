const BASE_URL = "http://localhost:8080/messages";

// Helper function to update output
const updateOutput = (message) => {
    const output = document.getElementById("output");
    output.textContent = typeof message === "string" ? message : JSON.stringify(message, null, 2);
};

// Create Message
document.getElementById("create").addEventListener("click", async () => {
    const content = document.getElementById("content").value;

    if (!content) {
        updateOutput("Content cannot be empty.");
        return;
    }

    const response = await fetch(BASE_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ content })
    });

    const result = await response.text();
    updateOutput(result);
});

// Read All Messages
document.getElementById("read-all").addEventListener("click", async () => {
    const response = await fetch(BASE_URL);
    const result = await response.json();
//    updateOutput(result);

    const contents = result.map(item => item.content);
    updateOutput(contents.join('\n'));

});

// Read Message by ID
document.getElementById("read-by-id").addEventListener("click", async () => {
    const id = document.getElementById("id").value;

    if (!id) {
        updateOutput("ID cannot be empty.");
        return;
    }

    const response = await fetch(`${BASE_URL}/${id}`);
    if (response.ok) {
        const result = await response.json();
        updateOutput(result);
    } else {
        updateOutput("Message not found.");
    }
});

// Update Message
document.getElementById("update").addEventListener("click", async () => {
    const id = document.getElementById("id").value;
    const content = document.getElementById("content").value;

    if (!id || !content) {
        updateOutput("ID and Content cannot be empty.");
        return;
    }

    const response = await fetch(`${BASE_URL}/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ content })
    });

    const result = await response.text();
    updateOutput(result);
});

// Delete Message
document.getElementById("delete").addEventListener("click", async () => {
    const id = document.getElementById("id").value;

    if (!id) {
        updateOutput("ID cannot be empty.");
        return;
    }

    const response = await fetch(`${BASE_URL}/${id}`, { method: "DELETE" });
    const result = await response.text();
    updateOutput(result);
});
