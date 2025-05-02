import scrapy
from bookscraper.items import BookItem

class BooksSpider(scrapy.Spider):
    name = "books"
    allowed_domains = ["books.toscrape.com"]
    start_urls = ["https://books.toscrape.com"]

    def parse(self, response):
        # Extract book links and follow them
        books = response.css('article.product_pod')
        for book in books:
            relative_url = book.css('h3 a::attr(href)').get()
            if relative_url:
                yield response.follow(relative_url, callback=self.parse_book)

        # Follow next page link
        next_page = response.css('li.next a::attr(href)').get()
        if next_page:
            yield response.follow(next_page, callback=self.parse)

    def parse_book(self, response):
        book_item = BookItem()
        
        book_item['title'] = response.css('h1::text').get()
        book_item['price'] = response.css('p.price_color::text').get()
        
        availability = response.css('p.instock.availability::text').getall()
        book_item['availability'] = ''.join(availability).strip()
        
        # Rating comes from the class name (e.g., "star-rating Three")
        rating_class = response.css('p.star-rating::attr(class)').get()
        book_item['rating'] = rating_class.split()[-1] if rating_class else None
        
        
        yield book_item