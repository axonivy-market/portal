// Constants
const URL_PATTERN = /\b(?:https?:\/\/www\.|https?:\/\/|www\.)[a-z0-9]+(?:[.\-][a-z0-9]+)*\.[a-z]{2,5}(?::[0-9]{1,5})?(?:\/[^()\s]*)?(?:(?:\([^()]*\))*)\b/ig;
const IMAGE_PATTERN = /\.(jpg|jpeg|png|webp|avif|gif|svg)$/;
const HAS_HTTP_PATTERN = /^https?:\/\//i;
const CODE_TAG = '```';
const IFRAME_TAG_START = '<iframe>';
const IFRAME_TAG_END = '</iframe>';
const HLJS_LANGUAGE_PREFIX = 'language-';

// Helper Functions

// Converts an HTML element to its string representation.
const elemToString = el => el.outerHTML;

// Checks if a paragraph starts with a code tag.
const isCode = paragraph => paragraph.startsWith(CODE_TAG);

// Checks if a URL is an image URL.
const isImageUrl = url => isUrl(url) && url.match(IMAGE_PATTERN);

// Checks if a paragraph starts with an iframe tag.
const isIFrame = paragraph => paragraph.startsWith(IFRAME_TAG_START);

// Adds a link component to a word if it's a URL, otherwise returns the word unchanged.
const addLink = word => (isUrl(word) ? generateLinkComponent(word) : word);

// Code Generation Functions

// Converts a code block paragraph to a formatted code component.
const convertCode = paragraph => {
  const removeCodeTags = paragraph => {
    var lines = paragraph.split('\n');

    if (lines.legnth <= 1) {
      return paragraph;
    }

    // Filter out lines start with the CODE_TAG
    var filteredLines = lines.filter(function(line) {
      return !line.startsWith(CODE_TAG);
    });

    // Join the filtered lines back into a single string
    return filteredLines.join('\n');
  };
  
  paragraph = removeCodeTags(paragraph);
  paragraph = generateCodeComponent(paragraph);
  return paragraph.endsWith(CODE_TAG) ? paragraph.slice(0, -CODE_TAG.length) : paragraph;
};

// Converts a iframe block paragraph to a Iframe component.
const convertIFrame = paragraph => {
  paragraph = paragraph.replace(IFRAME_TAG_START, '');
  paragraph = paragraph.replace(IFRAME_TAG_END, '');
  return generateIFrameComponent(paragraph);
}

// Generates an HTML link element with an updated URL.
const generateLinkComponent = url => {
  const updatedUrl = correctUrl(url);
  const link = $('.js-message-components').find('.js-external-link').get(0);
  const clone = link.cloneNode(true);
  clone.setAttribute('href', updatedUrl);
  clone.setAttribute('title', url);
  clone.innerHTML = url;
  clone.classList.remove('js-external-link');
  return elemToString(clone);
};

// Generates a formatted code component with syntax highlighting.
const generateCodeComponent = codeBlock => {
  // Adds a header with the code block language to the code component.
  const addCodeHeaderComponent = codeComponent => {
    const headerElem = document.createElement('div');
    headerElem.className = 'code-block-header';

    const headerTextElem = document.createElement('span');
    headerTextElem.textContent = getCodeClass(codeComponent);
    headerElem.appendChild(headerTextElem);

    codeComponent.innerHTML = headerElem.outerHTML + codeComponent.innerHTML;
  };

  // Retrieves the code block language from the code component.
  const getCodeClass = codeComponent => {
    const classList = codeComponent.className.split(' ');
    const languageClass = classList.find(className => className.startsWith(HLJS_LANGUAGE_PREFIX));
    return languageClass ? languageClass.substring(HLJS_LANGUAGE_PREFIX.length) : '';
  };

  // Wraps the content of the code component with a wrapper element.
  const wrapCodeContent = codeComponent => {
    const clonedChildNodes = [];

    while (codeComponent.firstChild) {
      const childNode = codeComponent.firstChild;
      const clonedNode = childNode.cloneNode(true);
      clonedChildNodes.push(clonedNode);
      codeComponent.removeChild(childNode);
    }

    const wrapperElem = document.createElement('div');
    wrapperElem.className = 'code-wrapper';

    for (const node of clonedChildNodes) {
      wrapperElem.appendChild(node);
    }

    codeComponent.appendChild(wrapperElem);
  };

  // Creates the code element, pre element, and adds syntax highlighting and wrapping.
  const codeElem = document.createElement('code');
  codeElem.innerHTML = codeBlock;

  const preElem = document.createElement('pre');
  preElem.appendChild(codeElem);
  preElem.className = 'code-block';

  // Highlights code
  hljs.highlightElement(preElem);

  // Wraps highlighted code
  wrapCodeContent(preElem);

  // Adds header
  addCodeHeaderComponent(preElem);

  return elemToString(preElem);
};

// Generates an Iframe component with the given paragraph
const generateIFrameComponent = paragraph => {
  const iframeElem = document.createElement('iframe');
  iframeElem.className = 'message-iframe';
  iframeElem.src = paragraph;
  return elemToString(iframeElem);
};

// Converts an image URL to a formatted image component.
const convertImage = paragraph => {
  const createImageContainerElement = () => {
    const imageContainer = document.createElement('div');
    imageContainer.className = 'image-container js-image-container';
    return imageContainer;
  };

  const image = document.createElement('img');
  image.src = paragraph;
  image.alt = paragraph;
  image.className = 'image js-image';

  const imageContainer = createImageContainerElement();
  imageContainer.appendChild(image);

  return elemToString(imageContainer);
};

const convertParagraph = paragraph => {
  const result = document.createElement('p');
  result.innerHTML = paragraph;
  return elemToString(result);
}

// Other Functions

// Corrects a URL by adding 'http://' if missing.
const correctUrl = url => url.match(URL_PATTERN);

// Checks if a word matches the URL pattern.
const isUrl = word => word.match(URL_PATTERN);

// Parse Functions

// Parses a paragraph based on its content type (code, image, or regular text).
const parseParagraph = paragraph => {
  paragraph = paragraph.trim();

  if (isCode(paragraph)) {
    paragraph = convertCode(paragraph);
  } else if (isImageUrl(paragraph)) {
    paragraph = convertImage(paragraph);
  } else if (isIFrame(paragraph)) {
    paragraph = convertIFrame(paragraph);
  } else {
    const words = paragraph.split(' ');
    const formattedWords = words.map(w => addLink(w));
    paragraph = formattedWords.join(' ');
    paragraph = convertParagraph(paragraph);
  }

  return paragraph;
};

// Parses a message by splitting it into paragraphs and formatting each paragraph.
const parseMessage = message => {
  const paragraphs = message.split(/\r?\n\n/);
  const formattedParagraphs = paragraphs.map(p => parseParagraph(p));
  message = formattedParagraphs.join('\r\n');
  return message;
};