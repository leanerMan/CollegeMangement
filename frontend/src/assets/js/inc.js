// $(function () {
//     var url = window.location.href;
    
//     // Main menu links
//     const allLinks = document.querySelectorAll('.menulink a');
//     const currentLink = [...allLinks].find(e => e.href === url);

//     if (currentLink) {
//         currentLink.classList.add("active");
//         //const parentTreeview = currentLink.closest(".has-treeview");
//         if (parentTreeview) {
//             parentTreeview.classList.add("active");
//         }
//     }

//     // Submenu links
//     const allSubLinks = document.querySelectorAll('.menulink .sublinks a');
//     const currentSubLink = [...allSubLinks].find(e => e.href === url);

//     if (currentSubLink) {
//         const sublinksParent = currentSubLink.closest(".sublinks");
//         if (sublinksParent) {
//             sublinksParent.classList.add("open");
//         }
//         currentSubLink.classList.add("active");
        
//         const subMenuParent = currentSubLink.closest(".sub-menu");
//         if (subMenuParent) {
//             subMenuParent.style.display = "block";
//         }
//     }
// });

$(document).ready(function () { 
    // AOS.init(); 
    $('.datetimepicker').datetimepicker(); 
    $('[data-toggle="modal"]').tooltip();
    $(".sub-menu").before("<div class='ddclick'></div>"); 

    $( ".ddclick" ).click(function() { 
        $(".sidebar-menu > li > ul").slideUp();
        //$('.sidelink > ul > li').removeClass('active');
      $(this).next().slideDown(); 
      $(this).addClass('active');
    });

    // $( ".ddclick" ).click(function() { 
    // $('.sidelink > ul > li').removeClass('active');
    // $(this).parent().addClass('active');
    // //$(".ddclick").removeClass('active');
    // //$(this).addClass('active'); 
    // //$(".sub-menu").slideUp();
    // $(this).next().slideDown(); 
    // });

    // $(".sidelink > ul > li.active").find('.sub-menu').slideDown();

    // $(".sidelink").click(function(e){
    // e.stopPropagation();
    // }); 
});

$(function () {
    var url = window.location.href;
    
    // Main menu links
    const allLinks = document.querySelectorAll('.sidebar-menu > li a');
    const currentLink = [...allLinks].find(e => e.href === url);

    if (currentLink) {
        currentLink.classList.add("active");
        const parentUl = currentLink.closest(".sidebar-menu > li");
        if (parentUl) {
            parentUl.classList.add("active");
        }

        const parentTreeview = currentLink.closest(".has-treeview");
        if (parentTreeview) {
            parentTreeview.classList.add("active");
        }
    }

    // Submenu links
    const allSubLinks = document.querySelectorAll('.sidebar-menu > li a');
    const currentSubLink = [...allSubLinks].find(e => e.href === url);

    if (currentSubLink) {
        const sublinksParent = currentSubLink.closest(".sidebar-menu > li");
        if (sublinksParent) {
            sublinksParent.classList.add("active");
        }
        currentSubLink.classList.add("active");
        
        const subMenuParent = currentSubLink.closest(".sidebar-menu > li > ul");
        if (subMenuParent) {

            subMenuParent.style.display = "block";
        }
    }
});




// $(".sub-menu").before("<div class='ddclick'></div>"); 
//     $( ".ddclick" ).click(function() { 
//     $('.sidelink > ul > li').removeClass('active');
//     $(this).parent().addClass('active');
//     //$(".ddclick").removeClass('active');
//     //$(this).addClass('active'); 
//     $(".sub-menu").slideUp();
//     $(this).next().slideDown(); 
//     });
//     $(".sidelink > ul > li.active").find('.sub-menu').slideDown();

//     $(".sidelink").click(function(e){
//     e.stopPropagation();
//     });





 document.addEventListener('DOMContentLoaded', function () {
    // Check the initial state from localStorage
    const isSidebarHidden = localStorage.getItem('sidebarHidden') === 'true';

    // Apply initial state
    toggleSidebar(isSidebarHidden);
});

function toggleSidebar(forceState) {
    //const sidebar = document.getElementById('leftmenu');
    const sidebar = document.querySelector('.leftmenu');
    const content = document.querySelector('.contentsection');

    // If forceState is provided, use it; otherwise, toggle the current state
    const newState = forceState !== undefined ? forceState : sidebar.style.right === '0px';

    // Set the state in localStorage
    localStorage.setItem('sidebarHidden', newState);

    // Apply the new state
    if (newState) {
      $('.leftmenu').addClass('active');
      $('.contentsection').addClass('active');
     // sidebar.addClass('active');
     //    content.addClass('active');
        sidebar.style.right = '1px';
       // sidebar.style.position = 'absolute';
        //content.style.width = '100%';
    } else {
      $('.leftmenu').removeClass('active');
      $('.contentsection').removeClass('active');
      // sidebar.removeClass('active');
      //   content.removeClass('active');
        sidebar.style.right = '0';
        //sidebar.style.position = 'relative';
        //content.style.width = 'calc(100% - 270px)';
    }
}
function includeHTML() {
  var z, i, elmnt, file, xhttp;
  /* Loop through a collection of all HTML elements: */
  z = document.getElementsByTagName("*");
  for (i = 0; i < z.length; i++) {
    elmnt = z[i];
    /*search for elements with a certain atrribute:*/
    file = elmnt.getAttribute("w3-include-html");
    if (file) {
      /* Make an HTTP request using the attribute value as the file name: */
      xhttp = new XMLHttpRequest();
      xhttp.onreadystatechange = function() {
        if (this.readyState == 4) {
          if (this.status == 200) {elmnt.innerHTML = this.responseText;}
          if (this.status == 404) {elmnt.innerHTML = "Page not found.";}
          /* Remove the attribute, and call this function once more: */
          elmnt.removeAttribute("w3-include-html");
          includeHTML();
        }
      }
      xhttp.open("GET", file, true);
      xhttp.send();
      /* Exit the function: */
      return;
    }
  }
}