void mainImage(out vec4 f, vec2 p)
{
    p=p+vec2(-iResolution.x, -iResolution.y);
    p=p/2e3-.2;

    float b = ceil(atan(p.x, p.y) * 6e2), h = cos(b), z = h / dot(p, p);
    f = vec4(0.0, 0.0, 0.1, 0.0) + exp(fract(z + h * b + vec4(iTime*.1)) * -2e3) / z;
}