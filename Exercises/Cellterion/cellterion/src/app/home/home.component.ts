import { Component, OnInit, AfterViewInit } from '@angular/core';
import { OwlOptions } from 'ngx-owl-carousel-o';
import * as AOS from 'aos';
declare var $: any;

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, AfterViewInit {
  customOptions: OwlOptions = {
    center: false,
    items: 1,
    loop: true,
    smartSpeed: 700,
    stagePadding: 15,
    margin: 20,
    autoplay: true,
    nav: true,
    navText: ['<span class="icon-chevron-left">', '<span class="icon-chevron-right">'],
    responsive: {
      600: {
        margin: 20,
        items: 2
      },
      1000: {
        margin: 20,
        items: 3
      },
      1200: {
        margin: 20,
        items: 3
      }
    }
  };

  constructor() {}

  ngOnInit(): void {
    AOS.init({
      duration: 800,
      easing: 'slide',
      once: true
    });

    $('#inpt_search').on('focus', function() {
      $(this).parent('label').addClass('active');
    });

    $('#inpt_search').on('blur', function() {
      if ($(this).val().length === 0) {
        $(this).parent('label').removeClass('active');
      }
    });

    // tslint:disable-next-line:only-arrow-functions
    $('#inpt_search').keydown(function(e) {
      // prevent: "e", "=", ",", "-", "."
      if ([69, 187, 188, 189, 190, 107, 109, 110].includes(e.keyCode)) {
        e.preventDefault();
      }
    });

    // tslint:disable-next-line:only-arrow-functions
    $(document).on('keypress', function(e) {
      if (e.which === 13) {
        // tslint:disable-next-line:only-arrow-functions
        $('#search_form').submit(function(ex) {
          ex.preventDefault(); // stop form submit and do your ajax call

          const searchData = $('#inpt_search').val();
          $.ajax({
            type: 'POST',
            url: 'servlet_home',
            data: {search: searchData},
            cache: false,
            success(response) {
              if (response === 'Connection Error!') {
                alert('Oops! Seems like we faced an internal server error! Please try performing the search again.');
              } else if (response === 'Invalid Input!') {
                // tslint:disable-next-line:max-line-length
                alert('Oops! No smartphones available for the requested price!\n\n - You probably entered a rather low price for a smartphone\n\n\nHint: Try entering a value between 10,000 and 300,000');
              } else if (response === 'No Internet!') {
                alert('Oops! Seems like there\'s no stable connection available! Please check your internet connection and try again.');
              } else if (response === 'No Smartphones!') {
                // tslint:disable-next-line:max-line-length
                alert('Oops! Seems like there are no smartphones available for the budget you provided. Please try performing the search again with a higher amount.');
              } else {
                location.href = 'servlet_budgetResults';
              }
            }
          });
        });
      }
    });
  }

  ngAfterViewInit(): void {
    'use strict';
  }
}
