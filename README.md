# DirectionalTextView

A flexible Android TextView that can display text in multiple orientations: vertically (up-to-down or down-to-up) and horizontally (left-to-right or right-to-left).

![directional_text_view](https://github.com/user-attachments/assets/5fafcf00-a857-43e7-a0e7-fd4ce1f34e7b)

## Features

- Four text orientation options:
  - Vertical: Up to Down (↓)
  - Vertical: Down to Up (↑)
  - Horizontal: Left to Right (→)
  - Horizontal: Right to Left (←)
- Automatic dimension calculations based on text orientation
- Proper text alignment and centering
- Easy integration with existing layouts
- Customizable via XML attributes or programmatically

## Installation

Add the view to your layout:

```xml
<com.muhammetkonukcu.views.DirectionalTextView
    android:id="@+id/directional_text"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Your text here"
    android:textSize="16sp"
    android:textColor="@color/black"
    app:direction="upToDown" />
```

## XML Attributes

Add this to your `attrs.xml` file if not already included:

```xml
<declare-styleable name="DirectionalTextView">
    <attr name="direction" format="enum">
        <enum name="upToDown" value="0" />
        <enum name="downToUp" value="1" />
        <enum name="leftToRight" value="2" />
        <enum name="rightToLeft" value="3" />
    </attr>
</declare-styleable>
```

## Usage

### Basic Usage

```xml
<!-- Vertical: Up to Down -->
<com.muhammetkonukcu.views.DirectionalTextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="VERTICAL TEXT"
    app:direction="upToDown" />

<!-- Vertical: Down to Up -->
<com.muhammetkonukcu.views.DirectionalTextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="VERTICAL TEXT"
    app:direction="downToUp" />

<!-- Horizontal: Left to Right (Standard) -->
<com.muhammetkonukcu.views.DirectionalTextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="HORIZONTAL TEXT"
    app:direction="leftToRight" />

<!-- Horizontal: Right to Left -->
<com.muhammetkonukcu.views.DirectionalTextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="HORIZONTAL TEXT"
    app:direction="rightToLeft" />
```

### Programmatic Usage

```kotlin
// Get reference to the view
val directionalText = binding.directionalTv

// Change orientation programmatically
directionalText.setOrientation(DirectionalTextView.ORIENTATION_UP_TO_DOWN)
directionalText.setOrientation(DirectionalTextView.ORIENTATION_DOWN_TO_UP)
directionalText.setOrientation(DirectionalTextView.ORIENTATION_LEFT_TO_RIGHT)
directionalText.setOrientation(DirectionalTextView.ORIENTATION_RIGHT_TO_LEFT)

// Standard TextView methods still work
directionalText.text = "New text content"
directionalText.setTextColor(Color.White)
directionalText.textSize = 18f
//
```

## Orientation Constants

```kotlin
companion object {
    const val ORIENTATION_UP_TO_DOWN = 0    // Vertical: top to bottom
    const val ORIENTATION_DOWN_TO_UP = 1    // Vertical: bottom to top
    const val ORIENTATION_LEFT_TO_RIGHT = 2 // Horizontal: standard direction
    const val ORIENTATION_RIGHT_TO_LEFT = 3 // Horizontal: reverse direction
}
```

## Example Applications

- Stylish headers in UI design
- Side navigation labels
- Creative typography for logos or branding
- Accordion-style UI elements
- Tab labels for vertical tabs
- Creative form designs

## Example Layout

```xml
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal"
    android:padding="16dp">

    <!-- Vertical label on the left -->
    <com.muhammetkonukcu.views.DirectionalTextView
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:background="@drawable/rounded_background"
        android:paddingHorizontal="8dp"
        android:text="SECTION TITLE"
        android:textColor="@color/white"
        android:textStyle="bold"
        app:direction="upToDown" />

    <!-- Content to the right -->
    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginStart="16dp"
        android:text="This is the main content area with regular horizontal text." />
</LinearLayout>
```

## Notes on Layout Dimensions

- For vertical text, consider that `layout_width` controls the space around the text height, and `layout_height` controls the space available for text length
- For horizontal text, normal TextView behavior applies
- Using `wrap_content` for both dimensions is recommended for most cases

## Contributing

Contributions are welcome! Feel free to submit a Pull Request.
