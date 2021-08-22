<html>
    <head>
        <title>Code</title>
        <link rel="stylesheet" target="_blank" href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
        <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
        <script>hljs.initHighlightingOnLoad();</script>
        <style>
            .span {
                font-family: monospace;
                font-size: 1.1rem;
                margin-top: 16px;
                display: block;
            }
            .code {
                background-color: #efefef;
                border: 1.9px solid gray;
                font-size: 1.1rem;
                padding: 10px;
                width: fit-content;
            }
            .date {
                color: green;
            }
        </style>
    </head>
    <body>
        <#if codeSnippet.hasSecretTime()>
            <span id="time_restriction" class="time">
                <p>The code will be available for ${codeSnippet.time} seconds</p>
            </span>
        </#if>
        <#if codeSnippet.hasSecretViews()>
            <span id="views_restriction" class="views">
                <p> ${codeSnippet.views} more views allowed</p>
            </span>
        </#if>
        <pre id="code_snippet" class="code">
            <code>
                ${codeSnippet.code}
            </code>
        </pre>
        <span id="load_date" class="date">
            ${codeSnippet.date}
           </span>
    </body>
</html>