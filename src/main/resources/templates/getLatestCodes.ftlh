<html>
    <head>
        <title>Latest</title>
        <link rel="stylesheet"
               target="_blank" href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
        <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
        <script>hljs.initHighlightingOnLoad();</script>
    </head>
    <body>
        <#list codeSnippetsList as codeSnippet>
                <pre id="code_snippet">
                    <code>
                        <td>${codeSnippet.code}</td>
                    <code>
                </pre>
                <span id="load_date">
                    <td>${codeSnippet.date}</td>
                </span>
        </#list>
    </body>
</html>
